package modmate.command;

import modmate.CommandCenter;
import modmate.log.LogUtil;
import modmate.user.User;

public class SetModLessonCommand extends Command {

    public static final String CLI_REPRESENTATION = "setlesson";

    private static final LogUtil logUtil = new LogUtil(ViewAllModsCommand.class);

    @Override
    public String getDescription() {
        return "Set lesson for each mod, by type.";
    }

    @Override
    public String getUsage() {
        return super.getUsage() + "  <timetable>: The name of the timetable.\n"
             + "  <mod code or name>: The code or name of the mod.\n"
             + "  <lesson type>: The type of lesson (e.g., Lecture, Tutorial).\n"
             + "  <lesson id>: The ID of the lesson to set.";
    }

    @Override
    public String getSyntax() {
        return CLI_REPRESENTATION + " <timetable> <mod code or name> <lesson type> <lesson id>";
    }

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
        logUtil.info("Setting mod lessons for: " + inputCodeOrName + "(" + timetable + ")");

        assert timetable != null
                && !timetable.trim().isEmpty() : "Timetable name cannot be null or empty";

        if (timetable.trim().isEmpty() || !currentUser.hasTimetable(timetable)) {
            System.out.println("Timetable \"" + timetable + "\" not found.");
            logUtil.info("Timetable '"
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
            logUtil.severe("Mod '" + inputCodeOrName + "' not found.");
        });
    }

}
