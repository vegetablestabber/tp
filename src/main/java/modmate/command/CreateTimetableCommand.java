package modmate.command;

import modmate.CommandCenter;
import modmate.log.LogUtil;
import modmate.user.User;

public class CreateTimetableCommand implements Command {

    public static final String CLI_REPRESENTATION = "createtimetable";

    private static final LogUtil logUtil = new LogUtil(CreateTimetableCommand.class);

    @Override
    public void execute(String[] args, User currentUser) {
        if (args.length < 2) {
            System.out.println("Usage: createtimetable <timetable>");
            return;
        }

        String inputTimetableName = CommandCenter.stringFromBetweenPartsXY(args, 1);

        assert inputTimetableName != null
                && !inputTimetableName.trim().isEmpty() : "Timetable name cannot be null or empty";
        logUtil.info("Creating timetable: " + inputTimetableName);

        if (currentUser.hasTimetable(inputTimetableName)) {
            System.out.println("Timetable with this name already exists.");
            logUtil.warning("Timetable " + inputTimetableName + " already exists.");
        } else {
            currentUser.addTimetable(inputTimetableName.replaceAll("\\s+", ""));
            logUtil.info("Timetable " + inputTimetableName + " created successfully.");
        }
    }
}
