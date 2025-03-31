package modmate;

import java.util.Scanner;

import modmate.command.Command;
import modmate.command.CommandLine;
import modmate.download.nusmods.NUSModsAPI;
import modmate.user.User;
import modmate.log.Log;

/**
 * Main entry point for the ModMate application. It handles user input
 * and executes commands such as viewing mods, adding/removing mods
 * to/from timetables, and managing bookmarks.
 */
public class Main {

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
            } else if (args[i].equals("--startYear") && i + 1 < args.length) {
                int startYear = Integer.parseInt(args[i + 1]);
                CommandCenter.allModCodesAndNames = NUSModsAPI.fetchAllModCodes(startYear);
            }
        }
        Log.printLog("Logging is enabled.");

        User currentUser = new User();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to ModMate!");

        boolean invalidCommand = false;

        while (true) {
            if (invalidCommand) {
                invalidCommand = false;
            } else {
                System.out.println("\nEnter command ('exit' to quit, '-h' for help):");
            }
            String input = scanner.nextLine().trim();
            Log.saveLog("\n[MAIN]   Received input: " + input);

            String[] inputParts = input.split(" ");
            String commandName = inputParts[0];

            Command command = CommandLine.getCommand(commandName);

            if (command != null) {
                command.execute(inputParts, currentUser);
            } else {
                switch (commandName) {
                case "-h" -> CommandCenter.printHelp();
                case "exit" -> {
                    Log.saveLog("[MAIN]   Exiting application.");
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }
                default -> {
                    System.out.println("Invalid command \""
                            + inputParts[0]
                            + "\"! Please check your command again, or run -h for help.");
                    invalidCommand = true;
                    Log.saveLog("[MAIN]   Command: " + input + " is invalid");
                }
                }
            }
        }
    }
}
