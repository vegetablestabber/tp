package modmate.command;

import modmate.CommandCenter;
import modmate.download.nusmods.NUSModsAPI;
import modmate.log.Log;
import modmate.mod.Mod;
import modmate.user.User;

import java.util.List;
import java.util.Optional;

public class SearchModCommand implements Command {

    @Override
    public void execute(String[] args, User currentUser) {
        if (args.length < 2) {
            System.out.println("Usage: searchmod <search query>");
            return;
        }

        String inputSearchQuery = args[1].trim();

        assert inputSearchQuery != null && !inputSearchQuery.isEmpty() : "Search query cannot be null or empty";

        Log.saveLog("[MAIN]   User is searching for a mod.");
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
        Log.saveLog("[MAIN]   Internally invoking search for " + searchTerm + ".");
        // Search inside Map allModCodesAndNames for matches
        // Will have to search through both halves of the map, code and name
        // If found, return list of getModFromAPIUsingCode(Code of Map pair found)
        // TODO Return a list of matching mods ordered by relevance
        return CommandCenter.allModCodesAndNames.values().stream().filter(
                        condensedMod -> condensedMod.getName().toLowerCase().contains(searchTerm.toLowerCase())
                                || condensedMod.getCode().toLowerCase().contains(searchTerm.toLowerCase())
                // map to full mods
                ).map(condensedMod -> NUSModsAPI.fetchModuleByCode(condensedMod.getCode()))
                // remove optionals
                .flatMap(Optional::stream)
                .toList();
    }
}
