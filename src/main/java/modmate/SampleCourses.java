package modmate;

import modmate.course.Course;

import java.util.List;

/**
 * A utility class that provides a sample list of courses.
 * This class is used to generate a list of courses with details like course code, name, description, and prerequisites.
 */
public class SampleCourses {

    /**
     * Returns a list of sample courses.
     * <p>
     * Each course is represented by a {@link Course} object containing the following details:
     * <ul>
     *     <li>Course code (e.g., "CS1010")</li>
     *     <li>Course name (e.g., "Programming Methodology")</li>
     *     <li>Course description (e.g., "Introduction to programming")</li>
     *     <li>Prerequisites (currently null for all courses)</li>
     * </ul>
     *
     * @return a list of {@link Course} objects representing sample courses
     */
    public static List<Course> getCourses() {
        return List.of(
                new Course("CS1010", "Programming Methodology", "Introduction to programming", null),
                new Course("CS2030", "Programming Methodology II", "Advanced programming", null),
                new Course("CS2103T", "Software Engineering", "Introduction to software engineering", null),
                new Course("CS2105", "Computer Networks", "Introduction to computer networks", null),
                new Course("CS2106", "Operating Systems", "Introduction to operating systems", null)
        );
    }
}
