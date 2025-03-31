package modmate.command;

import modmate.timetable.BreakPeriod;
import modmate.timetable.Period;
import modmate.user.User;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class AddBreakToTimetableCommand implements Command {

    @Override
    public void execute(String[] args, User currentUser) {
        if (args.length < 6) {
            System.out.println("Usage: addbreak <timetable> <label> <day> <startTime> <endTime>");
            return;
        }

        String timetableName = args[1];
        String label = args[2];

        assert timetableName != null && !timetableName.trim().isEmpty() : "Timetable name cannot be null or empty";
        assert label != null && !label.trim().isEmpty() : "Label cannot be null or empty";

        DayOfWeek day;
        LocalTime start;
        LocalTime end;

        try {
            day = DayOfWeek.valueOf(args[3].toUpperCase());

            assert day != null : "Day cannot be null";

            start = LocalTime.parse(args[4]);
            end = LocalTime.parse(args[5]);

            assert start != null && end != null : "Start or end time cannot be null";
            assert !end.isBefore(start) : "End time must be after start time";

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input format. Use format: MONDAY 12:00 13:00");
            return;
        }

        BreakPeriod breakPeriod = new BreakPeriod(label, new Period(day, start, end));
        boolean success = currentUser.addBreakToTimetable(timetableName, breakPeriod);

        if (success) {
            System.out.println("Break [" + label + "] added to timetable '" + timetableName + "'.");
        } else {
            System.out.println("Timetable '" + timetableName + "' not found.");
        }
    }
}
