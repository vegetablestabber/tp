package modmate.command;

import modmate.CommandCenter;
import modmate.log.LogUtil;
import modmate.timetable.Lesson;
import modmate.user.ScheduleMod;
import modmate.user.User;

import java.util.List;

public class ViewModLessonsCommand implements Command {

    public static final String CLI_REPRESENTATION = "viewlessons";
    private static final LogUtil logUtil = new LogUtil(ViewAllModsCommand.class);

    @Override
    public void execute(String[] args, User currentUser) {
        if (args.length < 1) {
            System.out.println("Usage: viewlessons <mod code or name>");
            return;
        }

        String inputCodeOrName = CommandCenter.stringFromBetweenPartsXY(args, 1);

        assert inputCodeOrName != null
                && !inputCodeOrName.trim().isEmpty() : "Mod code or name cannot be null or empty";
        logUtil.info("Viewing mod lessons for: " + inputCodeOrName);

        CommandCenter.modFromCodeOrName(inputCodeOrName).ifPresentOrElse(mod -> {
            ScheduleMod modSchedule = new ScheduleMod(mod);

            for (String type: modSchedule.getLessonTypes()) {
                System.out.println("[" + type + "]");
                List<Lesson> sortedLessons = modSchedule.getLessonsForType(type)
                    .stream().sorted().toList();
                for (Lesson lesson: sortedLessons) {
                    System.out.println("    " + lesson);
                }
            }
            logUtil.info("Mod lessons displayed.");
        }, () -> {
            System.out.println("Mod '" + inputCodeOrName + "' not found.");
            logUtil.severe("Mod '" + inputCodeOrName + "' not found.");
        });
    }
}
