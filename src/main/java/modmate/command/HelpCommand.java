package modmate.command;

import modmate.log.LogUtil;
import modmate.user.User;

public class HelpCommand implements Command {

    public static final String CLI_REPRESENTATION = "-h";

    private static final LogUtil logutil = new LogUtil(HelpCommand.class);

    private static final String helpMessage = """
            Commands:
            -h: Display this help message
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

    @Override
    public void execute(String[] args, User currentUser) {
        logutil.info("Printing help message.");
        System.out.println(helpMessage);
    }
}
