package modmate.event;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class Period {
    private DayOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;
    private HashMap<Integer, Boolean> occurrenceByWeek;

    private Period(DayOfWeek day, LocalTime startTime, LocalTime endTime, HashMap<Integer, Boolean> occurrenceByWeek) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.occurrenceByWeek = occurrenceByWeek;
    }

    public long getDuration() {
        return this.startTime.until(endTime, ChronoUnit.MINUTES);
    }

    public DayOfWeek getDay() {
        return day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public HashMap<Integer, Boolean> getOccurrenceByWeek() {
        return occurrenceByWeek;
    }
}
