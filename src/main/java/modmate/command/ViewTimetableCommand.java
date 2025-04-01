package modmate.command;

import modmate.CommandCenter;
import modmate.log.LogUtil;
import modmate.user.User;

public class ViewTimetableCommand implements Command {

    public static final String CLI_REPRESENTATION = "timetable";

    private static final LogUtil logUtil = new LogUtil(ViewTimetableCommand.class);

    @Override
    public void execute(String[] args, User currentUser) {
        if (args.length < 2) {
            System.out.println("Usage: timetable <timetable>");
            return;
        }

        String inputTimetableName = CommandCenter.stringFromBetweenPartsXY(args, 1);

        assert inputTimetableName != null
                && !inputTimetableName.trim().isEmpty() : "Timetable name cannot be null or empty";

        if (!currentUser.hasTimetable(inputTimetableName)) {
            System.out.println("Timetable \"" + inputTimetableName + "\" not found.");
            logUtil.warning("Timetable '" + inputTimetableName + "' not found.");
            return;
        }

        logUtil.info("Displaying user's mod list.");
        System.out.println(currentUser.getTimetable(inputTimetableName));
    }

}
