package team1.modmate.mod.modattributes;

/**
 * Represents a course with a specific name.
 * This class stores the name of the course and provides methods to get and set it.
 */
public class Course {
    private final String name;

    /**
     * Constructs a Course object with the specified course name.
     *
     * @param name the name of the course
     */
    public Course(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the course.
     *
     * @return the name of the course
     */
    public String getName() {
        return name;
    }
}
