package modmate.command;

import modmate.log.LogUtil;
import modmate.CommandCenter;
import modmate.user.User;

public class AddModToTimetableCommand extends Command {

    public static final String CLI_REPRESENTATION = "addmod";

    private static final LogUtil logUtil = new LogUtil(AddBreakToTimetableCommand.class);

    @Override
    public String getSyntax() {
        return CLI_REPRESENTATION + " <timetable name> <mod code or name>";
    }

    @Override
    public String getDescription() {
        return "Add a mod to your list.";
    }

    @Override
    public String getUsage() {
        return super.getUsage()
            + "  <timetable name>: The name of the timetable.\n"
            + "  <mod code or name>: The code or name of the mod to add.";
    }

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
            logUtil.warning("Timetable '"
                    + timetable
                    + "' not found while attempting to add mod "
                    + inputCodeOrName
                    + " to timetable.");
            return;
        }

        logUtil.info("Adding mod to timetable: " + timetable);

        CommandCenter.modFromCodeOrName(inputCodeOrName).ifPresentOrElse(mod -> {
            currentUser.addModToTimetable(timetable, mod);
            logUtil.info("Mod " + mod.getCode() + " added to timetable " + timetable);
        }, () -> {
            System.out.println("Mod '" + inputCodeOrName + "' not found.");
            logUtil.info("Mod '" + inputCodeOrName + "' not found.");
        });
    }

}
