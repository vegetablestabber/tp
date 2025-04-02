package modmate.command;

import modmate.log.LogUtil;
import modmate.CommandCenter;
import modmate.user.User;

public class RemoveModFromTimetableCommand implements Command {

    public static final String CLI_REPRESENTATION = "removemod";

    private static final LogUtil logUtil = new LogUtil(RemoveModFromTimetableCommand.class);

    @Override
    public void execute(String[] args, User currentUser) {
        if (args.length < 3) {
            System.out.println("Usage: removemod <timetable name> <mod code or name>");
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
            logUtil.severe("Timetable '"
                    + timetable
                    + "' not found while attempting to add mod "
                    + inputCodeOrName
                    + " to timetable.");
            return;
        }

        logUtil.info("Removing mod from timetable: " + timetable);

        CommandCenter.modFromCodeOrName(inputCodeOrName)
            .ifPresentOrElse(mod -> {
                currentUser.removeModFromTimetable(timetable, mod);
                logUtil.info("Mod " + mod.getCode() + " removed from timetable " + timetable);
            }, () -> {
                System.out.println("Mod '" + inputCodeOrName + "' not found.");
                logUtil.severe("Mod '" + inputCodeOrName + "' not found.");
            });
    }
}
