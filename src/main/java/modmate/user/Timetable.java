package modmate.user;

import modmate.course.Course;

import java.util.ArrayList;
import java.util.List;

public class Timetable {
    private String name; // Name of the timetable
    private List<Course> courses; // List of courses in this timetable

    /**
     * Constructs a new Timetable with the given name.
     *
     * @param name The name of the timetable.
     */
    public Timetable(String name) {
        this.name = name;
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        if (courses.contains(course)) {
            System.out.println("Course " + course.getCode() + " is already in the timetable.");
            return;
        }
        courses.add(course);
        System.out.println("Course " + course.getCode() + " added successfully to " + name);
    }

    public void removeCourse(Course course) {
        if (!courses.contains(course)) {
            System.out.println("Course " + course.getCode() + " is not in the timetable.");
            return;
        }
        courses.remove(course);
        System.out.println("Course " + course.getCode() + " removed successfully from " + name);
    }

    /**
     * Returns the name of the timetable.
     *
     * @return The name of the timetable.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the list of courses in the timetable.
     *
     * @return The list of courses.
     */
    public List<Course> getCourses() {
        return courses;
    }

    @Override
    public String toString() {
        String result = "Timetable: " + name + "\n";
        if (courses.isEmpty()) {
            result += "  No courses added yet.\n";
        } else {
            for (Course course : courses) {
                result += "  " + course.toString().replace("\n", "\n  ") + "\n";
            }
        }
        return result;
    }
}
