package modmate.timetable;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * Represents a period of time within a week, including the day, start and end times, and
 * the occurrence of this period across different weeks.
 */
public class Period {

    /**
     * The day of the week this period occurs on.
     */
    private DayOfWeek day;

    /**
     * The start time of the period.
     */
    private LocalTime startTime;

    /**
     * The end time of the period.
     */
    private LocalTime endTime;

    /**
     * A map representing whether this period occurs in a particular week.
     * The key is the week number, and the value indicates whether the period occurs in that week.
     */
    private WeekRange weekRange;

    /**
     * Constructs a period with a specified day, start time, end time,
     * and a week range indicating the occurrence of the period in specific weeks.
     *
     * @param day The day of the week the period occurs.
     * @param startTime The start time of the period.
     * @param endTime The end time of the period.
     * @param weekRange The week range indicating the occurrence of the period in specific weeks.
     */
    public Period(DayOfWeek day, LocalTime startTime, LocalTime endTime, WeekRange weekRange) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.weekRange = weekRange;
    }

    public Period(DayOfWeek day, LocalTime startTime, LocalTime endTime) {
        this(day, startTime, endTime, new WeekRange());
    }

    /**
     * Gets the duration of the period in minutes.
     *
     * @return The duration in minutes between the start and end times.
     */
    public long getDuration() {
        return this.startTime.until(endTime, ChronoUnit.MINUTES);
    }

    /**
     * Gets the day of the week the period occurs on.
     *
     * @return The day of the week.
     */
    public DayOfWeek getDay() {
        return day;
    }

    /**
     * Gets the start time of the period.
     *
     * @return The start time of the period.
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Gets the end time of the period.
     *
     * @return The end time of the period.
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Gets the map of occurrences for this period by week.
     *
     * @return A map where the key is the week number, and the value indicates whether the
     *         period occurs in that week.
     */
    public WeekRange getWeekRange() {
        return weekRange;
    }

    @Override
    public String toString() {
        return "Weeks " + weekRange.toString() + ", " + day + ", " + startTime + "-" + endTime;
    }

    public boolean isClashing(Period period) {
        return period.getDay().equals(this.day) &&
                period.getWeekRange().isClashing(this.weekRange) &&
                period.getStartTime().isBefore(this.endTime) &&
                period.getEndTime().isAfter(this.startTime);

    }
}
