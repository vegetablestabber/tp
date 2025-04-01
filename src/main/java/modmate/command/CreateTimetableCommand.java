package modmate.command;

import modmate.CommandCenter;
import modmate.log.Log;
import modmate.user.User;

public class CreateTimetableCommand implements Command {

    @Override
    public void execute(String[] args, User currentUser) {
        if (args.length < 2) {
            System.out.println("Usage: createtimetable <timetable>");
            return;
        }

        String inputTimetableName = CommandCenter.stringFromBetweenPartsXY(args, 1);

        assert inputTimetableName != null
                && !inputTimetableName.trim().isEmpty() : "Timetable name cannot be null or empty";
        Log.saveLog("[MAIN]   Creating timetable: " + inputTimetableName);

        if (currentUser.hasTimetable(inputTimetableName)) {
            System.out.println("Timetable with this name already exists.");
            Log.saveLog("[MAIN]   Timetable " + inputTimetableName + " already exists.");
        } else {
            currentUser.addTimetable(inputTimetableName.replaceAll("\\s+", ""));
            Log.saveLog("[MAIN]   Timetable " + inputTimetableName + " created successfully.");
        }
    }
}
