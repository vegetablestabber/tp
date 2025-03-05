package modmate;

import modmate.course.Course;

import java.util.List;

public class SampleCourses {
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
