package modmate.timetable;

public class BreakPeriod {
    private final String label;  // e.g. "Lunch", "Gym Break"
    private final Period period;

    public BreakPeriod(String label, Period period) {
        this.label = label;
        this.period = period;
    }

    public String getLabel() {
        return label;
    }

    public Period getPeriod() {
        return period;
    }

    @Override
    public String toString() {
        return "[" + label + "] " + period.getDay() + ": " +
                period.getStartTime() + "–" + period.getEndTime();
    }
}
