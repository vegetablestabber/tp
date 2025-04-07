package modmate.ui;

import modmate.command.BookmarkCommand;
import modmate.command.Command;
import modmate.command.ExitCommand;
import modmate.command.GetBookmarksCommand;
import modmate.command.HelpCommand;
import modmate.command.RemoveBookmarkCommand;
import modmate.command.SearchModCommand;
import modmate.command.ViewAllModsCommand;
import modmate.command.ViewModCommand;
import modmate.command.search.SearchModCommand;
import modmate.exception.CommandException;
import modmate.command.AddModToTimetableCommand;
import modmate.command.RemoveModFromTimetableCommand;
import modmate.command.AddBreakToTimetableCommand;

public class CommandParser {

    public static Command parse(Input input)
            throws CommandException, IllegalArgumentException {
        Command command;

        switch (input.getCommand()) {
        case HelpCommand.CLI_REPRESENTATION -> command = new HelpCommand(input);
        case AddBreakToTimetableCommand.CLI_REPRESENTATION -> command = new AddBreakToTimetableCommand(input);
        case AddModToTimetableCommand.CLI_REPRESENTATION -> command = new AddModToTimetableCommand(input);
        case BookmarkCommand.CLI_REPRESENTATION -> command = new BookmarkCommand(input);
        case GetBookmarksCommand.CLI_REPRESENTATION -> command = new GetBookmarksCommand(input);
        case SearchModCommand.CLI_REPRESENTATION -> command = new SearchModCommand(input);
        case ViewModCommand.CLI_REPRESENTATION -> command = new ViewModCommand(input);
        case RemoveBookmarkCommand.CLI_REPRESENTATION -> command = new RemoveBookmarkCommand();
        case SetModLessonCommand.CLI_REPRESENTATION -> command = new SetModLessonCommand(input);
        case ViewModLessonsCommand.CLI_REPRESENTATION -> command = new ViewModLessonsCommand(input);
        case RemoveModFromTimetableCommand.CLI_REPRESENTATION -> command = new RemoveModFromTimetableCommand(input);
        case CreateTimetableCommand.CLI_REPRESENTATION -> command = new CreateTimetableCommand(input);
        case ViewTimetableCommand.CLI_REPRESENTATION -> command = new ViewTimetableCommand(input);
        case ViewAllModsCommand.CLI_REPRESENTATION -> command = new ViewAllModsCommand(input);
        case ExitCommand.CLI_REPRESENTATION -> command = new ExitCommand(input);
        default -> throw new IllegalArgumentException("Input error: Invalid input");
        }

        return command;
    }

}
