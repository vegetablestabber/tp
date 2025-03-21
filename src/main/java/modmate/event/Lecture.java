package modmate.event;

/**
 * Represents a lecture event
 * <p>
 * A lecture is a scheduled event, and it extends the {@link Event} class.
 * </p>
 */
public class Lecture extends Event {

    /**
     * Constructs a Lecture object with the specified details.
     *
     * @param period the time period when the lecture occurs, including the day, start time, and duration
     * @param location the location where the lecture takes place
     */
    public Lecture(Period period, String location, String classNo) {
        super(period, location, classNo);
    }
}
