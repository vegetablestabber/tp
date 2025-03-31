package modmate.command;

import modmate.CommandCenter;
import modmate.log.Log;
import modmate.user.User;

public class ViewTimetableCommand implements Command {

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
            Log.saveLog("[MAIN]   Timetable '" + inputTimetableName + "' not found.");
            return;
        }

        Log.saveLog("[MAIN]   Displaying user's mod list.");
        System.out.println(currentUser.getTimetable(inputTimetableName));
    }
}
