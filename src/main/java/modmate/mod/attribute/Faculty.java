package modmate.mod.attribute;

/**
 * Represents a faculty with a specific name.
 * This class stores the name of the faculty and provides methods to retrieve
 * and update it.
 */
public class Faculty {
    private final String name;

    /**
     * Constructs a Faculty object with the specified faculty name.
     *
     * @param name the name of the faculty
     */
    public Faculty(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Faculty other) {
            return this.name.equalsIgnoreCase(other.name);
        }

        return false;
    }

    /**
     * Gets the name of the faculty.
     *
     * @return the name of the faculty
     */
    public String getName() {
        return this.name;
    }

    /**
     * Creates a new Faculty instance with an updated name.
     *
     * @param newName the new name of the faculty
     * @return a new Faculty instance with the updated name
     */
    public Faculty updateName(String newName) {
        return new Faculty(newName);
    }

    /**
     * Returns a string representation of the faculty.
     *
     * @return a string representation of the faculty
     */
    public String toString() {
        return this.name;
    }
}
