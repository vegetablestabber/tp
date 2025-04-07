package modmate.command;

import modmate.command.search.SearchModCommand;
import modmate.exception.CommandException;
import modmate.log.LogUtil;
import modmate.ui.Input;
import modmate.user.User;

public class HelpCommand extends Command {

    public static final String CLI_REPRESENTATION = "help";

    private static final LogUtil logutil = new LogUtil(HelpCommand.class);

    private static final String helpMessage = """
            Commands:
            help: Display this help message
            exit: Exit the application
            viewmod <mod code or name>: View details of a mod by its mod code or name
            viewlessons <mod code or name>: View available lessons for each mod, sorted by type
            setlesson <timetable> <mod code or name> <lesson type> <lesson id>: Set lesson for each mod, by type
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

    private final String commandToReceiveHelpFor;

    public HelpCommand(Input input) {
        super(input);
        this.commandToReceiveHelpFor = input.getArgument().trim();
    }

    @Override
    public String getSyntax() {
        return CommandUtil.buildSyntax(CLI_REPRESENTATION);
    }

    @Override
    public String getDescription() {
        return "Display this help message.";
    }

    @Override
    public void execute(User currentUser) {
        logutil.info("Executing help command.");

        if (commandToReceiveHelpFor.isEmpty()) {
            // Display general help message
            System.out.println(helpMessage);
            return;
        }

        // Match the argument to a command's CLI_REPRESENTATION
        Command command;
        try {
            switch (commandToReceiveHelpFor) {
            case CLI_REPRESENTATION -> command = new HelpCommand(input);
            case ExitCommand.CLI_REPRESENTATION -> command = new ExitCommand(input);
            case BookmarkCommand.CLI_REPRESENTATION -> command = new BookmarkCommand(input);
            case GetBookmarksCommand.CLI_REPRESENTATION -> command = new GetBookmarksCommand(input);
            case SearchModCommand.CLI_REPRESENTATION -> command = new SearchModCommand(input);
            case ViewModCommand.CLI_REPRESENTATION -> command = new ViewModCommand(input);
            case ViewAllModsCommand.CLI_REPRESENTATION -> command = new ViewAllModsCommand(input);
            default -> throw new IllegalArgumentException("Command not found: " + commandToReceiveHelpFor);
            }
        } catch (IllegalArgumentException e) {
            logutil.severe("Invalid command: " + commandToReceiveHelpFor);
            throw new CommandException(this, e.getMessage());
        }

        // Display the usage of the matched command
        System.out.println(command.getUsage());
    }
}
