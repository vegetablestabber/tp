package modmate;

import java.util.List;
import java.util.Scanner;

import modmate.mod.Mod;
import modmate.user.User;
import modmate.log.Log;

/**
 * Main entry point for the ModMate application. It handles user input
 * and executes commands such as viewing mods, adding/removing mods
 * to/from timetables, and managing bookmarks.
 */
public class Main {

    static String helpMessage = """
        Commands:
        -h: Display this help message
        exit: Exit the application
        viewmod <mod code/name>: View details of a mod by its mod code
        bookmark <mod code/name>: Bookmark a mod for later reference
        bookmarks: View all bookmarked mods
        addmod <timetable> <mod_code>: Add a mod to your list
        removemod <timetable> <mod code/name>: Remove a mod from your list
        createtimetable <timetable>: Create a new timetable
        timetable <timetable>: Display your mod timetable
        viewallmods: View all available mods
        searchmod <mod code/name>: Search for a mod by its code or name
        """;

    static List<Mod> allMods = SampleMods.getMods();

    /**
     * The main command loop of the application that processes user input
     * and executes corresponding actions based on the command received.
     *
     * @param args Command-line arguments (for logging configuration).
     */
    public static void main(String[] args) {

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--log") && i + 1 < args.length) {
                Log.setLoggingEnabled(Boolean.parseBoolean(args[i + 1]));
            }
        }
        Log.printLog("Logging is enabled.");

        User currentUser = new User();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to ModMate!");

        while (true) {
            System.out.println("Enter command ('exit' to quit, '-h' for help):");
            String input = scanner.nextLine().trim().toLowerCase();
            Log.saveLog("\n[MAIN]   Received input: " + input);

            String[] inputParts = input.split(" "); // Split command and argument

            switch (inputParts[0]) {
            case "-h" -> printHelp();
            case "viewmod" -> viewMod(stringFromBetweenPartsXY(inputParts, 1));
            case "bookmark" -> bookmark(stringFromBetweenPartsXY(inputParts, 1), currentUser);
            case "bookmarks" -> getBookmarks(currentUser);
            case "addmod" -> addModToTimetable(
                    inputParts[1],
                    stringFromBetweenPartsXY(inputParts, 2),
                    currentUser
            );
            case "removemod" -> removeModFromTimetable(
                    inputParts[1],
                    stringFromBetweenPartsXY(inputParts, 2),
                    currentUser
            );
            case "createtimetable" -> createTimetable(stringFromBetweenPartsXY(inputParts, 1), currentUser);
            case "timetable" -> viewTimetable(stringFromBetweenPartsXY(inputParts, 1), currentUser);
            case "viewallmods" -> viewAllMods();
            case "searchmod" -> searchMods(stringFromBetweenPartsXY(inputParts, 1), currentUser);

            case "exit" -> {
                Log.saveLog("[MAIN]   Exiting application.");
                System.out.println("Exiting...");
                scanner.close();
                return;
            }
            default -> Log.saveLog("[MAIN]   Command: " + input + " is invalid");
            }
        }
    }

    /**
     * Prints the help message for available commands.
     */
    private static void printHelp() {
        Log.saveLog("[MAIN]   Printing help message.");
        System.out.println(helpMessage);
    }

    /**
     * Helper method that searches for an exact matching mod by its code or name.
     *
     * @param modCode The code or name of the mod to search for.
     * @return The mod that matches the given code or name, or null if no match is found.
     */
    private static Mod modFromNameOrCode(String modCode) {
        for (Mod mod : allMods) {
            if (mod.getName().trim().equalsIgnoreCase(modCode)
                    || mod.getCode().trim().equalsIgnoreCase(modCode)) {
                return mod;
            }
        }
        return null;
    }

    /**
     * Helper method to extract a substring from the command input.
     *
     * @param inputParts The split command input.
     * @param x The starting index of the substring.
     * @return The concatenated string from index x to the end of the input.
     */
    private static String stringFromBetweenPartsXY(String[] inputParts, int x) {
        return stringFromBetweenPartsXY(inputParts, x, inputParts.length);
    }

    /**
     * Helper method to extract a substring from the command input between two indices.
     *
     * @param inputParts The split command input.
     * @param x The starting index of the substring.
     * @param y The ending index of the substring.
     * @return The concatenated string between indices x and y in the input.
     */
    private static String stringFromBetweenPartsXY(String[] inputParts, int x, int y) {
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

    /**
     * Displays details of a mod given its mod code.
     *
     * @param inputNameOrCode The mod code or name to search for.
     */
    private static void viewMod(String inputNameOrCode) {
        Log.saveLog("[MAIN]   Viewing mod details.");
        Mod mod = modFromNameOrCode(inputNameOrCode);

        if (mod != null) {
            System.out.println(mod);
        } else {
            System.out.println("Mod '" + inputNameOrCode + "' not found.");
        }
    }

    /**
     * Bookmarks a mod for later reference.
     *
     * @param inputNameOrCode The mod code or name to bookmark.
     * @param currentUser The user object representing the current user.
     */
    private static void bookmark(String inputNameOrCode, User currentUser) {
        Log.saveLog("[MAIN]   Bookmarking mod.");
        // Bookmark a course for later reference

        Course course = allCourses
                .stream()
                .filter(c ->
                        c.getCode().equalsIgnoreCase(courseCode))
                .findFirst()
                .orElse(null);

        if (course == null) {
            Log.saveLog("[MAIN]   Course to bookmark not found.");
            System.out.println("Course with code '" + courseCode + "' not found.");
        } else {
            currentUser.addBookmark(course);
            System.out.println("Bookmark " + courseCode + " successfully added to your list.");
        }
    }

    /**
     * Displays all mods bookmarked by the user.
     *
     * @param currentUser The user object representing the current user.
     */
    private static void getBookmarks(User currentUser) {
        Log.saveLog("[MAIN]   Viewing bookmarks.");

        // View all bookmarked courses
        List<Course> bookmarks = currentUser.getBookmarks();

        System.out.println("You have " + bookmarks.size() + " bookmarks.");

        for (Course course : bookmarks) {
            System.out.println(course);
        }
    }

    /**
     * Adds a mod to the user's timetable.
     *
     * @param timetable The name of the timetable to which the mod should be added.
     * @param inputNameOrCode The mod code or name to add to the timetable.
     * @param currentUser The user object representing the current user.
     */
    private static void addModToTimetable(String timetable, String inputNameOrCode, User currentUser) {
        Log.saveLog("[MAIN]   Adding mod to timetable.");
        Mod mod = modFromNameOrCode(inputNameOrCode);

        if (mod != null) {
            currentUser.addModToTimetable(timetable, mod);
        } else {
            System.out.println("Mod '" + inputNameOrCode + "' not found.");
        }
    }

    /**
     * Removes a mod from the user's timetable.
     *
     * @param timetable The name of the timetable from which the mod should be removed.
     * @param inputNameOrCode The mod code or name to remove from the timetable.
     * @param currentUser The user object representing the current user.
     */
    private static void removeModFromTimetable(String timetable, String inputNameOrCode, User currentUser) {
        Mod mod = modFromNameOrCode(inputNameOrCode);

        if (mod != null) {
            currentUser.removeModFromTimetable(timetable, mod);
        } else {
            System.out.println("Mod '" + inputNameOrCode + "' not found.");
        }
    }

    /**
     * Creates a new timetable for the user.
     *
     * @param inputTimetableName The name of the new timetable to create.
     * @param currentUser The user object representing the current user.
     */
    private static void createTimetable(String inputTimetableName, User currentUser) {
        Log.saveLog("[MAIN]   Creating timetable.");
        currentUser.addTimetable(inputTimetableName.replaceAll("\\s+", ""));
        // Removing all whitespaces from the timetable name to avoid issues with the add and remove methods
    }

    /**
     * Displays the user's timetable for a specific timetable name.
     *
     * @param inputTimetableName The name of the timetable to display.
     * @param currentUser The user object representing the current user.
     */
    private static void viewTimetable(String inputTimetableName, User currentUser) {
        Log.saveLog("[MAIN]   Displaying user's mod list.");
        System.out.println(currentUser.getTimetable(inputTimetableName));
    }

    /**
     * Displays all available mods.
     */
    private static void viewAllMods() {
        Log.saveLog("[MAIN]   Viewing all mods.");
        for (Mod mod : allMods) {
            System.out.println(mod);
        }
    }

    /**
     * Searches for mods by mod code or name.
     *
     * @param inputSearchQuery The search term to search for (mod code or name).
     * @param currentUser The user object representing the current user.
     */
    private static void searchMods(String inputSearchQuery, User currentUser) {
        Log.saveLog("[MAIN]   User is searching for a mod.");
        List<Mod> searchResults = getSearchResults(inputSearchQuery);
        if (searchResults != null) {
            for (Mod mod : searchResults) {
                System.out.println(mod);
            }
        } else {
            System.out.println("No mods found.");
        }
    }

    /**
     * Searches for mods that match the given search term.
     *
     * @param searchTerm The term to search for (mod code or name).
     * @return A list of matching mods.
     */
    private static List<Mod> getSearchResults(String searchTerm) {
        Log.saveLog("[MAIN]   Internally invoking search for " + searchTerm + ".");
        // TODO Search for mods that contain the search term in their mod code or name
        // TODO Return a list of matching mods ordered by relevance
        return null;
    }
}
