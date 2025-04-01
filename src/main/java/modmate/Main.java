package modmate;

import java.util.Scanner;

import modmate.command.CommandLine;
import modmate.download.nusmods.NUSModsAPI;
import modmate.log.LogUtil;
import modmate.user.User;

/**
 * Main entry point for the ModMate application. It handles user input
 * and executes commands such as viewing mods, adding/removing mods
 * to/from timetables, and managing bookmarks.
 */
public class Main {

    private static final LogUtil log = new LogUtil(Main.class);

    /**
     * The main command loop of the application that processes user input
     * and executes corresponding actions based on the command received.
     *
     * @param args Command-line arguments (for logging configuration).
     */
    public static void main(String[] args) {

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--startYear") && i + 1 < args.length) {
                int startYear = Integer.parseInt(args[i + 1]);
                CommandCenter.allModCodesAndNames = NUSModsAPI.fetchAllModCodes(startYear);
                log.info("Start year set to: " + startYear);
            }
        }

        User currentUser = new User();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to ModMate!");

        while (true) {
            System.out.println("\nEnter command ('exit' to quit, '-h' for help):");

            String input = scanner.nextLine().trim();
            log.info("Received input: " + input);

            String[] inputParts = input.split(" ");
            String commandName = inputParts[0];

            // Process the command
            if (CommandLine.isValidCommand(commandName)) {
                CommandLine.getCommand(commandName).ifPresent(
                    command -> command.execute(inputParts, currentUser)
                );
            } else if (printUtilityCommandOutput(commandName, input)) {
                break; // Exit the loop if the exit command is issued
            } else {
                System.out.println("Invalid command \"" + commandName
                        + "\"! Please check your command again, or run -h for help.");
                log.warning("Invalid command: " + input);
            }
        }

        scanner.close();
    }

    /**
     * Handles utility commands like help and exit.
     *
     * @param commandName The command name entered by the user.
     * @param input       The full input string entered by the user.
     * @return true if the application should exit, false otherwise.
     */
    public static boolean printUtilityCommandOutput(String commandName, String input) {
        switch (commandName) {
        case "-h" -> {
            CommandCenter.printHelp();
            log.info("Help command executed.");
        }
        case "exit" -> {
            log.info("Exiting application.");
            System.out.println("Exiting...");
            return true; // Signal to exit the application
        }
        default -> {
            return false; // Not a utility command
        }
        }
        return false; // Default return for non-exit commands
    }
}