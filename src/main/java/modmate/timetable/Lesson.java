package modmate.timetable;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        return compareParts(this.id, other.id);
    }

    private static int compareParts(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return 0;
        }
        if (s1.isEmpty()) {
            return 1;
        }
        if (s2.isEmpty()) {
            return -1;
        }
        ArrayList<String> part1 = extractInitial(s1);
        ArrayList<String> part2 = extractInitial(s2);

        int result = part1.get(0).compareTo(part2.get(0));
        if (result != 0) {
            return result;
        }

        return compareRemainingParts(part1.get(1), part2.get(1));
    }

    private static ArrayList<String> extractInitial(String s) {
        ArrayList<String> parts = new ArrayList<>();
        Pattern pattern = Pattern.compile("^([A-Za-z]+|\\d+)(.*)$");
        Matcher matcher = pattern.matcher(s);

        if (matcher.find()) {
            parts.add(matcher.group(1)); // The initial part (either letters or digits)
            parts.add(matcher.group(2)); // The remaining part
        } else {
            parts.add(""); // Default if no match
            parts.add(s);  // Whole string as the remaining part
        }

        return parts;
    }

    private static int compareRemainingParts(String s1, String s2) {
        if (s1.isEmpty()) {
            return -1;
        }
        if (s2.isEmpty()) {
            return 1;
        }

        if (isNumeric(s1) && isNumeric(s2)) {
            return Integer.compare(Integer.parseInt(s1), Integer.parseInt(s2));
        }
        if (isAlphabetic(s1) && isAlphabetic(s2)) {
            return s1.compareTo(s2);
        }

        if (isNumeric(s1) && isAlphabetic(s2)) {
            return -1;
        }
        if (isAlphabetic(s1) && isNumeric(s2)) {
            return 1;
        }

        return 0;
    }

    private static boolean isNumeric(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isAlphabetic(String s) {
        return s.matches("[A-Za-z]+");
    }


    public String toString() {
        return "[" + id + "] " + period + " @ " + venue;
    }
}
