package modmate.event;

/**
 * Represents a tutorial event.
 */
public class Tutorial extends Event {

    /**
     * Constructs a Tutorial object with the specified details.
     *
     * @param period the period when the tutorial occurs
     * @param location the location where the tutorial takes place
     */
    public Tutorial(Period period, String location, String classNo) {
        super(period, location, classNo);
    }

}
