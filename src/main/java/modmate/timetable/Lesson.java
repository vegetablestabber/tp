package modmate.timetable;

/**
 * Represents an lesson with a unique identifier, period, and venue.
 */
public class Lesson implements Comparable<Lesson> {

    /**
     * The type of the lesson (e.g., "Lecture", "Tutorial").
     */
    private final String type;

    /**
     * The unique identifier for the lesson.
     */
    private final String id;

    /**
     * The period when this lesson takes place.
     */
    private final Period period;

    /**
     * The location where the lesson takes place.
     */
    private final String venue;

    /**
     * Constructs an lesson object with the specified details.
     *
     * @param type   the type of the lesson (e.g., "Lecture", "Tutorial")
     * @param id     the unique identifier for the lesson
     * @param period the period when this lesson takes place
     * @param venue  the location where the lesson takes place
     */
    public Lesson(String type, String id, Period period, String venue) {
        this.type = type;
        this.id = id;
        this.period = period;
        this.venue = venue;
    }

    /**
     * Returns the type of the lesson.
     *
     * @return the type of the lesson as a String
     */
    public String getType() {
        return this.type;
    }

    /**
     * Gets the period when the lesson occurs.
     *
     * @return the period during which the lesson takes place
     */
    public Period getPeriod() {
        return this.period;
    }

    /**
     * Gets the location of the lesson.
     *
     * @return the location of the lesson
     */
    public String getVenue() {
        return this.venue;
    }

    /**
     * Gets the class number of the lesson.
     *
     * @return the class number of the lesson
     */
    public String getId() {
        return this.id;
    }

    @Override
    public int compareTo(Lesson other) {
        // This is just to compare class numbers in the format of "1A", "1B", "1C", etc.

        String[] parts1 = this.id.split("(?<=\\d)(?=\\D)");
        String[] parts2 = other.id.split("(?<=\\d)(?=\\D)");

        int numComparison = Integer.compare(Integer.parseInt(parts1[0]), Integer.parseInt(parts2[0]));

        if (numComparison != 0) {
            return numComparison;
        }

        if (parts1.length > 1 && parts2.length > 1) {
            return parts1[1].compareTo(parts2[1]);
        } else if (parts1.length > 1) {
            return 1;
        } else if (parts2.length > 1) {
            return -1;
        }

        return 0;
    }

}
