package modmate.course.attribute;

/**
 * Represents a school with a specific name.
 * This class stores the name of the school and provides methods to get and set it.
 */
public class Faculty {
    private final String name;

    /**
     * Constructs a School object with the specified school name.
     *
     * @param name the name of the school
     */
    public Faculty(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the school.
     *
     * @return the name of the school
     */
    public String getName() {
        return this.name;
    }

    public Faculty updateName(String newName) {
        return new Faculty(newName);
    }
}
