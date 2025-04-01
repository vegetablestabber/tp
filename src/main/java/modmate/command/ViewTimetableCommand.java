package modmate.command;

import modmate.CommandCenter;
import modmate.log.Log;
import modmate.mod.Mod;
import modmate.timetable.Lesson;
import modmate.user.Schedule;
import modmate.user.User;

import java.time.DayOfWeek;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class ViewTimetableCommand implements Command {

    @Override
    public void execute(String[] args, User currentUser) {
        if (args.length < 2) {
            System.out.println("Usage: timetable <timetable>");
            return;
        }

        String inputTimetableName = CommandCenter.stringFromBetweenPartsXY(args, 1);
        String viewtype = "default";
        if (args[1].equals("timeline")) {
            viewtype = args[1];
            inputTimetableName = CommandCenter.stringFromBetweenPartsXY(args, 2);
        }

        assert inputTimetableName != null
                && !inputTimetableName.trim().isEmpty() : "Timetable name cannot be null or empty";

        Schedule timetable = currentUser.getTimetable(inputTimetableName);
        if (!currentUser.hasTimetable(inputTimetableName) || timetable == null) {
            System.out.println("Timetable \"" + inputTimetableName + "\" not found.");
            Log.saveLog("[MAIN]   Timetable '" + inputTimetableName + "' not found.");
            return;
        }

        Log.saveLog("[MAIN]   Displaying user's mod list.");
        if (viewtype.equals("timeline")) {
            HashMap<DayOfWeek, List<AbstractMap.SimpleEntry<Mod, Lesson>>> timeline = new HashMap<>();
            timetable.getMods().forEach(
                    mod -> {
                        for (String type: timetable.getModSchedule(mod).getLessonTypes()) {
                            for (Lesson lesson: timetable.getModSchedule(mod).getSelectedLessons(type)){
                                DayOfWeek day = lesson.getPeriod().getDay();
                                if (!timeline.containsKey(day)) {
                                    timeline.put(day, new ArrayList<>());
                                }
                                timeline.get(day).add(
                                        new AbstractMap.SimpleEntry<>(mod, lesson)
                                );
                            }
                        }

                    }
            );


            for (DayOfWeek day : DayOfWeek.values()) {
                if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
                    continue;
                }
                System.out.println("[" + day + "]");
                List<AbstractMap.SimpleEntry<Mod, Lesson>> lessons = timeline.get(day).stream().sorted(
                        Comparator.comparing(a -> a.getValue().getPeriod().getStartTime())
                ).toList();

                for (AbstractMap.SimpleEntry<Mod, Lesson> lesson: lessons) {
                    System.out.println(" | " + lesson.getKey().getCode() + ": " + lesson.getValue());
                }

            }
        } else {
            System.out.println(timetable.toString());
        }
    }
}
