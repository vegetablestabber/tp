package modmate;

import java.util.List;
import java.util.Scanner;

import modmate.user.User;
import modmate.course.Course;
import modmate.log.Log;

/**
 * Main entry point for the ModMate application. It handles user input
 * and executes commands such as viewing courses, adding/removing courses
 * to/from timetables, and managing bookmarks.
 */
public class Main {

    static String helpMessage = """
        Commands:
        -h: Display this help message
        exit: Exit the application
        viewmod <course_code>: View details of a mod by its course code
        bookmark <course_code>: Bookmark a course for later reference
        bookmarks: View all bookmarked courses
        addmod <timetable> <course_name>: Add a mod to your list
        removemod <timetable> <course_name>: Remove a mod from your list
        createtimetable <timetable>: Create a new timetable
        timetable <timetable>: Display your mod timetable
        viewallmods: View all available mods
        searchmod <course_code_or_name>: Search for a mod by its code or name
        """;

    static List<Course> allCourses = SampleCourses.getCourses();

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
            case "viewmod" -> viewCourse(stringFromBetweenPartsXY(inputParts, 1));
            case "bookmark" -> bookmark(stringFromBetweenPartsXY(inputParts, 1), currentUser);
            case "bookmarks" -> getBookmarks(currentUser);
            case "addmod" -> addCourseToTimetable(
                    inputParts[1],
                    stringFromBetweenPartsXY(inputParts, 2),
                    currentUser
            );
            case "removemod" -> removeCourseFromTimetable(
                    inputParts[1],
                    stringFromBetweenPartsXY(inputParts, 2),
                    currentUser
            );
            case "createtimetable" -> createTimetable(stringFromBetweenPartsXY(inputParts, 1), currentUser);
            case "timetable" -> viewTimetable(stringFromBetweenPartsXY(inputParts, 1), currentUser);
            case "viewallmods" -> viewAllCourses();
            case "searchmod" -> searchCourses(stringFromBetweenPartsXY(inputParts, 1), currentUser);

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
     * Helper method that searches for an exact matching course by its code or name.
     *
     * @param courseCode The code or name of the course to search for.
     * @return The course that matches the given code or name, or null if no match is found.
     */
    private static Course courseFromNameOrCode(String courseCode) {
        for (Course course : allCourses) {
            if (course.getName().trim().equalsIgnoreCase(courseCode)
                    || course.getCode().trim().equalsIgnoreCase(courseCode)) {
                return course;
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
     * Displays details of a course given its course code.
     *
     * @param inputNameOrCode The course code or name to search for.
     */
    private static void viewCourse(String inputNameOrCode) {
        Log.saveLog("[MAIN]   Viewing mod details.");
        Course course = courseFromNameOrCode(inputNameOrCode);

        if (course != null) {
            System.out.println(course);
        } else {
            System.out.println("Course '" + inputNameOrCode + "' not found.");
        }
    }

    /**
     * Bookmarks a course for later reference.
     *
     * @param inputNameOrCode The course code or name to bookmark.
     * @param currentUser The user object representing the current user.
     */
    private static void bookmark(String inputNameOrCode, User currentUser) {
        Log.saveLog("[MAIN]   Bookmarking mod.");
        // Bookmark a course for later reference
        // TODO Add course to user's bookmarks
    }

    /**
     * Displays all courses bookmarked by the user.
     *
     * @param currentUser The user object representing the current user.
     */
    private static void getBookmarks(User currentUser) {
        Log.saveLog("[MAIN]   Viewing bookmarks.");
        // View all bookmarked courses
        // TODO Print all courses in user's bookmarks
    }

    /**
     * Adds a course to the user's timetable.
     *
     * @param timetable The name of the timetable to which the course should be added.
     * @param inputNameOrCode The course code or name to add to the timetable.
     * @param currentUser The user object representing the current user.
     */
    private static void addCourseToTimetable(String timetable, String inputNameOrCode, User currentUser) {
        Log.saveLog("[MAIN]   Adding course to timetable.");
        Course course = courseFromNameOrCode(inputNameOrCode);

        if (course != null) {
            currentUser.addCourseToTimetable(timetable, course);
        } else {
            System.out.println("Course '" + inputNameOrCode + "' not found.");
        }
    }

    /**
     * Removes a course from the user's timetable.
     *
     * @param timetable The name of the timetable from which the course should be removed.
     * @param inputNameOrCode The course code or name to remove from the timetable.
     * @param currentUser The user object representing the current user.
     */
    private static void removeCourseFromTimetable(String timetable, String inputNameOrCode, User currentUser) {
        Course course = courseFromNameOrCode(inputNameOrCode);

        if (course != null) {
            currentUser.removeCourseFromTimetable(timetable, course);
        } else {
            System.out.println("Course '" + inputNameOrCode + "' not found.");
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
     * Displays all available courses.
     */
    private static void viewAllCourses() {
        Log.saveLog("[MAIN]   Viewing all mods.");
        for (Course course : allCourses) {
            System.out.println(course);
        }
    }

    /**
     * Searches for courses by course code or name.
     *
     * @param inputSearchQuery The search term to search for (course code or name).
     * @param currentUser The user object representing the current user.
     */
    private static void searchCourses(String inputSearchQuery, User currentUser) {
        Log.saveLog("[MAIN]   User is searching for a mod.");
        List<Course> searchResults = getSearchResults(inputSearchQuery);
        if (searchResults != null) {
            for (Course course : searchResults) {
                System.out.println(course);
            }
        } else {
            System.out.println("No mods found.");
        }
    }

    /**
     * Searches for courses that match the given search term.
     *
     * @param searchTerm The term to search for (course code or name).
     * @return A list of matching courses.
     */
    private static List<Course> getSearchResults(String searchTerm) {
        Log.saveLog("[MAIN]   Internally invoking search for " + searchTerm + ".");
        // TODO Search for courses that contain the search term in their course code or name
        // TODO Return a list of matching courses ordered by relevance
        return null;
    }
}
