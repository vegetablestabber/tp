package modmate.command;

import modmate.CommandCenter;
import modmate.download.nusmods.NUSModsAPI;
import modmate.log.LogUtil;
import modmate.mod.CondensedMod;
import modmate.mod.Mod;
import modmate.user.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SearchModCommand implements Command {

    public static final String CLI_REPRESENTATION = "searchmod";

    private static final LogUtil logUtil = new LogUtil(SearchModCommand.class);

    @Override
    public void execute(String[] args, User currentUser) {
        if (args.length < 2) {
            System.out.println("Usage: searchmod <search query>");
            return;
        }

        String inputSearchQuery = args[1].trim();

        assert inputSearchQuery != null && !inputSearchQuery.isEmpty() : "Search query cannot be null or empty";

        logUtil.info("User is searching for a mod.");
        List<Mod> searchResults = getSearchResults(inputSearchQuery);

        if (!searchResults.isEmpty()) {
            for (Mod mod : searchResults) {
                System.out.println(mod);
            }
        } else {
            System.out.println("No mods found matching the search query.");
        }

    }

    private static List<Mod> getSearchResults(String searchTerm) {
        logUtil.info("Internally invoking search for " + searchTerm + ".");
        // Search inside Map allModCodesAndNames for matches
        // Will have to search through both halves of the map, code and name
        // If found, return list of getModFromAPIUsingCode(Code of Map pair found)

        ArrayList<Mod> filteredMods = new ArrayList<>();

        CommandCenter.allModCodesAndNames.values()
            .parallelStream()
            .filter(condensedMod -> {
                String lowerCaseSearchTerm = searchTerm.toLowerCase();
                String lowerCaseModCode = condensedMod.getCode().toLowerCase();
                String lowerCaseModName = condensedMod.getName().toLowerCase();

                return lowerCaseModCode.contains(lowerCaseSearchTerm)
                    || lowerCaseModName.contains(lowerCaseSearchTerm);
            })
            .forEach(condensedMod -> {
                Optional<Mod> modOptional = NUSModsAPI.fetchModuleByCode(condensedMod.getCode());
                modOptional.ifPresent(mod -> filteredMods.add(mod));
            });

        filteredMods.sort(new Comparator<CondensedMod>() {
            public int compare(CondensedMod o1, CondensedMod o2) {
                return o1.getCode().compareTo(o2.getCode());
            };
        });

        return filteredMods;
    }
}
