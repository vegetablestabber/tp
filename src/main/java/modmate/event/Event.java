package modmate.event;

/**
 * Represents a generic class that occurs on a specific day, at a specific time,
 * with a given duration, cancellation weeks, and location.
 * This class is abstract and can be extended to define specific types of events.
 */
public abstract class Event {
    private final String classNo;
    private final Period period;
    private String venue;

    /**
     * Constructs an Event object with the specified details.
     *
     * @param period the period when this event takes place
     * @param venue the location where the event takes place
     */
    public Event(Period period, String venue, String classNo) {
        this.period = period;
        this.venue = venue;
        this.classNo = classNo;
    }

    /**
     * Gets the period when the event occurs.
     *
     * @return the period during which the event takes place
     */
    public Period getPeriod() {
        return this.period;
    }

    /**
     * Gets the location of the event.
     *
     * @return the location of the event
     */
    public String getVenue() {
        return venue;
    }

    /**
     * Gets the class number of the event.
     *
     * @return the class number of the event
     */
    public String getClassNo() {
        return classNo;
    }

    /**
     * Sets the location of the event.
     *
     * @param venue the new location of the event
     */
    public void setVenue(String venue) {
        this.venue = venue;
    }
}
