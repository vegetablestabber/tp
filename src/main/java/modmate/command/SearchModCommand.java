package modmate.command;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import modmate.download.nusmods.NUSModsAPI;
import modmate.exception.ApiException;
import modmate.exception.CommandException;
import modmate.log.LogUtil;
import modmate.mod.CondensedMod;
import modmate.mod.attribute.ModAttributes;
import modmate.timetable.Semester;
import modmate.ui.Input;
import modmate.ui.InputParser;
import modmate.ui.Pagination;
import modmate.ui.ProgressBar;
import modmate.user.User;

public class SearchModCommand extends Command {

    private final String identifier;
    private final ModAttributes attributes;

    public static final String CLI_REPRESENTATION = "searchmod";

    private static final LogUtil logUtil = new LogUtil(SearchModCommand.class);

    public SearchModCommand(Input input) {
        super(input);

        this.identifier = input.getArgument();
        this.attributes = new ModAttributes(
                input.getFlag("faculty"),
                input.getFlag("semesters")
                        .map(str -> Arrays.asList(str.split("\\s+"))
                                .stream()
                                .map(numAsStr -> Integer.valueOf(numAsStr))
                                .map(num -> Semester.fromInt(num))
                                .toList())
                        .orElse(Collections.emptyList()),
                input.getFlag("units"),
                input.getFlag("graded"),
                Optional.empty());

        if (this.identifier.isEmpty() && !this.attributes.hasAtLeastOneAttribute()) {
            throw new CommandException(this,
                    "Search query cannot be empty or have no filter conditions");
        }
    }

    @Override
    public String getSyntax() {
        // TODO: Expand on what formats are allowed for the flag parameters
        String str1 = CommandUtil.concatenate(
                CLI_REPRESENTATION,
                "<search query>");

        String str2 = CommandUtil.concatenate(
                InputParser.FLAG_PREFIX + "faculty <faculty>",
                InputParser.FLAG_PREFIX + "semesters <semesters>",
                InputParser.FLAG_PREFIX + "units <units>",
                InputParser.FLAG_PREFIX + "graded <graded>");

        return str1 + "[" + str2.toString() + "]";
    }

    @Override
    public String getDescription() {
        return "Search for a mod by its code or name.";
    }

    @Override
    public String getUsage() {
        return super.getUsage() + "  <mod code or name>: The code or name of the mod to search for.\n"
             + "  --faculty <faculty>: Optional. Filter by faculty.\n"
             + "  --semesters <semesters>: Optional. Filter by semesters (space-separated).\n"
             + "  --units <units>: Optional. Filter by modular credits.\n"
             + "  --graded <graded>: Optional. Filter by grading type.";
    }

    @Override
    public void execute(User currentUser) throws ApiException {
        logUtil.info("User is searching for a mod.");

        int totalModules = NUSModsAPI.condensedMods.size();
        if (totalModules == 0) {
            throw new ApiException("No modules available to search.");
        }

        List<CondensedMod> searchResults = getSearchResultsWithProgress(input.getArgument(), totalModules);

        if (searchResults.isEmpty()) {
            System.out.println("\nNo mods found matching the search query.");
        } else {
            System.out.println("\nSearch results:");
            Pagination<CondensedMod> pagination = new Pagination<>(searchResults, 15);
            pagination.display();
        }
    }

    private List<CondensedMod> getSearchResultsWithProgress(String searchTerm, int totalModules) {
        logUtil.info("Internally invoking search for " + searchTerm + ".");

        Stream<? extends CondensedMod> condensedModStream = NUSModsAPI.condensedMods
                .values()
                .stream()
                .parallel();

        condensedModStream = identifier.isEmpty()
                ? condensedModStream
                : condensedModStream.filter(mod -> doesIdentifierMatch(mod));

        List<CondensedMod> filteredMods;

        // Thread-safe counter for progress
        final int[] processedCount = { 0 };

        if (this.attributes.hasAtLeastOneAttribute()) {
            condensedModStream = condensedModStream
                    .map(condensedMod -> NUSModsAPI.fetchModuleByCode(condensedMod.getCode()))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .filter(mod -> this.attributes.equals(mod.getAttributes()));
        }

        filteredMods = condensedModStream
                .peek(mod -> {
                    // Update progress bar
                    synchronized (processedCount) {
                        processedCount[0]++;
                        ProgressBar.print(processedCount[0], totalModules);
                    }
                })
                .collect(Collectors.toList());

        filteredMods.sort(Comparator.comparing(CondensedMod::getCode));
        return filteredMods;
    }

    private boolean doesIdentifierMatch(CondensedMod condensedMod) {
        String lowerCaseIdentifier = this.identifier.toLowerCase();
        String lowerCaseModCode = condensedMod.getCode().toLowerCase();
        String lowerCaseModName = condensedMod.getName().toLowerCase();

        return lowerCaseModCode.contains(lowerCaseIdentifier)
                || lowerCaseModName.contains(lowerCaseIdentifier);
    }

}
