package modmate.command;

import java.util.HashMap;
import java.util.Map;

public class CommandLine {
    private static final Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("viewmod", new ViewModCommand());
        commands.put("viewlessons", new ViewModLessonsCommand());
        commands.put("setlesson", new SetModLessonCommand());
        commands.put("bookmark", new BookmarkCommand());
        commands.put("bookmarks", new GetBookmarksCommand());
        commands.put("searchmod", new SearchModCommand());
        commands.put("addmod", new AddModToTimetableCommand());
        commands.put("removemod", new RemoveModFromTimetableCommand());
        commands.put("createtimetable", new CreateTimetableCommand());
        commands.put("timetable", new ViewTimetableCommand());
        commands.put("addbreak", new AddBreakToTimetableCommand());
        commands.put("viewallmods", new ViewAllModsCommand());
    }

    public static Command getCommand(String commandName) {
        return commands.getOrDefault(commandName, null);
    }
}
