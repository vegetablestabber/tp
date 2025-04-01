package modmate.command;

import modmate.CommandCenter;
import modmate.log.Log;
import modmate.user.User;

public class SetModLessonCommand implements Command {

    @Override
    public void execute(String[] args, User currentUser) {
        if (args.length < 4) {
            System.out.println("Usage: setlesson <timetable> <mod code or name> <lesson type> <lesson id>");
            return;
        }

        String timetable = CommandCenter.stringFromBetweenPartsXY(args, 1, 2);
        String inputCodeOrName = CommandCenter.stringFromBetweenPartsXY(args, 2, 3);
        String lessonType = CommandCenter.stringFromBetweenPartsXY(args, 3, args.length - 1);
        String lessonId = CommandCenter.stringFromBetweenPartsXY(args, args.length - 1);

        assert inputCodeOrName != null
                && !inputCodeOrName.trim().isEmpty() : "Mod code or name cannot be null or empty";
        Log.saveLog("[MAIN]   Setting mod lessons for: " + inputCodeOrName + "(" + timetable + ")");

        assert timetable != null
                && !timetable.trim().isEmpty() : "Timetable name cannot be null or empty";

        if (timetable.trim().isEmpty() || !currentUser.hasTimetable(timetable)) {
            System.out.println("Timetable \"" + timetable + "\" not found.");
            Log.saveLog("[MAIN]   Timetable '"
                    + timetable
                    + "' not found while attempting to setting lesson for mod "
                    + inputCodeOrName
                    + ".");
            return;
        }

        CommandCenter.modFromCodeOrName(inputCodeOrName).ifPresentOrElse(mod -> {
            currentUser.setLessonOnTimetable(timetable, mod, lessonType, lessonId);
        }, () -> {
            System.out.println("Mod '" + inputCodeOrName + "' not found.");
            Log.saveLog("[MAIN]   Mod '" + inputCodeOrName + "' not found.");
        });
    }
}
