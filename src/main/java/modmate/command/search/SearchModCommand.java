package modmate.command.search;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import modmate.command.Command;
import modmate.command.CommandUtil;
import modmate.command.util.Flag;
import modmate.download.nusmods.NUSModsAPI;
import modmate.exception.ApiException;
import modmate.exception.CommandException;
import modmate.log.LogUtil;
import modmate.mod.CondensedMod;
import modmate.mod.attribute.Faculty;
import modmate.mod.attribute.ModAttributes;
import modmate.timetable.Semester;
import modmate.ui.Input;
import modmate.ui.Pagination;
import modmate.user.User;

public class SearchModCommand extends Command {

    private final String identifier;

    public static final String CLI_REPRESENTATION = "searchmod";

    private static final LogUtil logUtil = new LogUtil(SearchModCommand.class);

    private final Flag<Faculty> facultyFlag;
    private final Flag<List<Semester>> semestersFlag;
    private final Flag<Double> unitsFlag;
    private final Flag<Boolean> gradedFlag;

    public SearchModCommand(Input input) {
        super(input);

        this.facultyFlag = SearchFlagBuilder.createFacultyFlag(input.getFlag("faculty"));
        this.semestersFlag = SearchFlagBuilder.createSemestersFlag(input.getFlag("semesters"));
        this.unitsFlag = SearchFlagBuilder.createUnitsFlag(input.getFlag("units"));
        this.gradedFlag = SearchFlagBuilder.createGradedFlag(input.getFlag("graded"));

        this.identifier = input.getArgument();

        if (this.identifier.isEmpty() && !hasAtLeastOneFlag()) {
            throw new CommandException(this, "Search query cannot be empty or have no filter conditions");
        }
    }

    private boolean hasAtLeastOneFlag() {
        return facultyFlag.getValue().isPresent()
            || semestersFlag.getValue().isPresent()
            || unitsFlag.getValue().isPresent()
            || gradedFlag.getValue().isPresent();
    }

    @Override
    public String getSyntax() {
        return CommandUtil.buildSyntax(CLI_REPRESENTATION, "search query", List.of(facultyFlag, semestersFlag, unitsFlag, gradedFlag));
    }

    @Override
    public String getDescription() {
        return "Search for a mod by its code or name.";
    }

    @Override
    public String getUsage() {
        return super.getUsage(List.of(facultyFlag, semestersFlag, unitsFlag, gradedFlag));
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

        condensedModStream = SearchUtil.filterByIdentifier(condensedModStream, identifier);

        if (hasAtLeastOneFlag()) {
            ModAttributes attributes = SearchUtil.createAttributes(facultyFlag, semestersFlag, unitsFlag, gradedFlag);
            condensedModStream = SearchUtil.filterByAttributes(condensedModStream, attributes);
        }

        List<CondensedMod> filteredMods = SearchUtil.collectWithProgress(condensedModStream, totalModules);

        filteredMods.sort(Comparator.comparing(CondensedMod::getCode));
        return filteredMods;
    }
}
