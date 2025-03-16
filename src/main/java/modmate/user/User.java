package modmate.user;

import modmate.course.Course;

import java.util.ArrayList;
import java.util.List;

public class User {
    private List<Course> bookmarks;

    // List to store the user's timetables
    private List<Timetable> timetables;

    // Constructor to initialize the user's courses and timetables
    public User() {
        this.timetables = new ArrayList<>();
        this.bookmarks = new ArrayList<>();
    }

    // Method to add a new timetable for the user
    public void addTimetable(String timetableName) {
        for (Timetable timetable : timetables) {
            if (timetable.getName().equalsIgnoreCase(timetableName)) {
                System.out.println("A timetable with the '" + timetable + "' already exists");
                return;
            }
        }
        Timetable newtimetable = new Timetable(timetableName);
        timetables.add(newtimetable);
        System.out.println("Timetable '" + timetableName + "' created successfully");
        // TODO add a new timetable with name to timetables
    }

    // Method to display the user's courses and timetables
    public String getTimetable(String timetableName) {
        for (Timetable timetable : timetables) {
            if (timetable.getName().equalsIgnoreCase(timetableName)) {
                return timetable.toString();
            }
        }
        return "Timetable '" + timetableName + "' not found";
        //return("Noone has implemented me yet!! AAARGH!!");
        // TODO print timetable if it exists, otherwise just print "nooooo" or something
    }

    // Add course to timetables
    public void addCourseToTimetable(String timetableName, Course course) {
        for (Timetable timetable : timetables) {
            if (timetable.getName().equalsIgnoreCase(timetableName)) {
                timetable.addCourse(course);
                return;
            }
        }
        System.out.println("Timetable '" + timetableName + "' not found.");
    }

    //Remove course from timetables
    public void removeCourseFromTimetable(String timetableName, Course course) {
        for (Timetable timetable : timetables) {
            if (timetable.getName().equalsIgnoreCase(timetableName)) {
                timetable.removeCourse(course);
                return;
            }
        }
        System.out.println("Timetable '" + timetableName + "' not found.");
    }


    // TODO getters and setters for bookmarks, timetables, and
}
