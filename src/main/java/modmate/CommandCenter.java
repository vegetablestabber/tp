package modmate;

import modmate.command.Command;
import modmate.command.CommandLine;
import modmate.download.nusmods.NUSModsAPI;
import modmate.log.Log;
import modmate.mod.CondensedMod;
import modmate.mod.Mod;
import modmate.user.User;

import java.util.Map;
import java.util.Optional;

public class CommandCenter {
    public static Map<String, CondensedMod> allModCodesAndNames = NUSModsAPI.fetchAllModCodes();

    static String helpMessage = """
            Commands:
            -h: Display this help message
            exit: Exit the application
            viewmod <mod code or name>: View details of a mod by its mod code or name
            viewlessons <mod code or name>: View available lessons for each mod, sorted by type
            setlessons <mod code or name> <
            searchmod <mod code or name>: Search for a mod by its code or name
            bookmark <mod code or name>: Bookmark a mod for later reference
            bookmarks: View all bookmarked mods
            addmod <timetable> <mod code or name>: Add a mod to your list
            removemod <timetable> <mod code or name>: Remove a mod from your list
            createtimetable <timetable>: Create a new timetable
            addbreak <timetable> <label> <day> <startTime> <endTime>: Add a break
            timetable <timetable>: Display your mod timetable
            viewallmods: View all available mods
            """;

    static void printHelp() {
        Log.saveLog("[MAIN]   Printing help message.");
        System.out.println(helpMessage);
    }

    public static void handleCommand(String commandName, String[] args, User currentUser) {
        Command command = CommandLine.getCommand(commandName);

        if (command != null) {
            command.execute(args, currentUser);
        } else {
            // If the command does not exist, inform the user.
            System.out.println("Invalid command: " + commandName);
        }
    }

    /**
     * Helper method that searches for an exact matching mod by its code or name.
     *
     * @param modCodeOrNameGiven The code or name of the mod to search for.
     * @return The mod that matches the given code or name, or null if no match is
     *         found.
     */
    public static Optional<Mod> modFromCodeOrName(String modCodeOrNameGiven) {
        // First, check for a match with the module code (key)
        Optional<CondensedMod> condensedMod = Optional.ofNullable(
                allModCodesAndNames.get(modCodeOrNameGiven.toUpperCase())
        );

        // If a match is found, retrieve mod details using the module code
        return condensedMod.flatMap(mod -> NUSModsAPI.fetchModuleByCode(mod.getCode()));
    }

    /**
     * Helper method to extract a substring from the command input.
     *
     * @param inputParts The split command input.
     * @param x          The starting index of the substring.
     * @return The concatenated string from index x to the end of the input.
     */
    public static String stringFromBetweenPartsXY(String[] inputParts, int x) {
        return stringFromBetweenPartsXY(inputParts, x, inputParts.length);
    }

    /**
     * Helper method to extract a substring from the command input between two
     * indices.
     *
     * @param inputParts The split command input.
     * @param x          The starting index of the substring.
     * @param y          The ending index of the substring.
     * @return The concatenated string between indices x and y in the input.
     */
    public static String stringFromBetweenPartsXY(String[] inputParts, int x, int y) {
        if (inputParts == null || inputParts.length == 0 || x < 0 || y > inputParts.length || x >= y) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = x; i < y; i++) {
            sb.append(inputParts[i]);
            if (i < y - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
