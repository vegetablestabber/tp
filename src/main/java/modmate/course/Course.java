package modmate.course;

import modmate.course.attribute.CourseAttributes;

/**
 * Represents a course with a specific name.
 * This class stores the name of the course and provides methods to get and set it.
 */
public class Course {
    private final String name;
    private final String code;
    private final String description;
    private final CourseAttributes attributes;

    public Course(String name, String code, String description, CourseAttributes attributes) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.attributes = attributes;
    }

    public String getName() {
        return this.name;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public CourseAttributes getAttributes() {
        return attributes;
    }
}
