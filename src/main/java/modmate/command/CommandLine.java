package modmate.command;

import java.util.Optional;

public class CommandLine {

    public static Optional<Command> getCommand(String commandName) {
        Command command;

        switch (commandName) {
        case HelpCommand.CLI_REPRESENTATION -> command = new HelpCommand();
        case AddBreakToTimetableCommand.CLI_REPRESENTATION -> command = new AddBreakToTimetableCommand();
        case AddModToTimetableCommand.CLI_REPRESENTATION -> command = new AddModToTimetableCommand();
        case BookmarkCommand.CLI_REPRESENTATION -> command = new BookmarkCommand();
        case GetBookmarksCommand.CLI_REPRESENTATION -> command = new GetBookmarksCommand();
        case SearchModCommand.CLI_REPRESENTATION -> command = new SearchModCommand();
        case ViewModCommand.CLI_REPRESENTATION -> command = new ViewModCommand();
        case SetModLessonCommand.CLI_REPRESENTATION -> command = new SetModLessonCommand();
        case ViewModLessonsCommand.CLI_REPRESENTATION -> command = new ViewModLessonsCommand();
        case RemoveModFromTimetableCommand.CLI_REPRESENTATION -> command = new RemoveModFromTimetableCommand();
        case CreateTimetableCommand.CLI_REPRESENTATION -> command = new CreateTimetableCommand();
        case ViewTimetableCommand.CLI_REPRESENTATION -> command = new ViewTimetableCommand();
        case ViewAllModsCommand.CLI_REPRESENTATION -> command = new ViewAllModsCommand();
        default -> command = null;
        }

        return Optional.ofNullable(command);
    }

    public static boolean isValidCommand(String commandName) {
        return getCommand(commandName).isPresent();
    }
}
