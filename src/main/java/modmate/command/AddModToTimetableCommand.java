package modmate.command;

import modmate.log.Log;
import modmate.CommandCenter;
import modmate.user.User;

public class AddModToTimetableCommand implements Command {

    @Override
    public void execute(String[] args, User currentUser) {
        if (args.length < 3) {
            System.out.println("Usage: addmod <timetable name> <mod code or name>");
            return;
        }

        String timetable = args[1];
        String inputCodeOrName = CommandCenter.stringFromBetweenPartsXY(args, 2);

        assert timetable != null
                && !timetable.trim().isEmpty() : "Timetable name cannot be null or empty";
        assert inputCodeOrName != null
                && !inputCodeOrName.trim().isEmpty() : "Mod code or name cannot be null or empty";

        if (timetable.trim().isEmpty() || !currentUser.hasTimetable(timetable)) {
            System.out.println("Timetable \"" + timetable + "\" not found.");
            Log.saveLog("[MAIN]   Timetable '"
                    + timetable
                    + "' not found while attempting to add mod "
                    + inputCodeOrName
                    + " to timetable.");
            return;
        }
        Log.saveLog("[MAIN]   Adding mod to timetable: " + timetable);

        CommandCenter.modFromCodeOrName(inputCodeOrName).ifPresentOrElse(mod -> {
            currentUser.addModToTimetable(timetable, mod);
            Log.saveLog("[MAIN]   Mod " + mod.getCode() + " added to timetable " + timetable);
        }, () -> {
            System.out.println("Mod '" + inputCodeOrName + "' not found.");
            Log.saveLog("[MAIN]   Mod '" + inputCodeOrName + "' not found.");
        });
    }
}
