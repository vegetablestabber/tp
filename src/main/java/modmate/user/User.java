package modmate.user;

import modmate.mod.Mod;
import modmate.timetable.BreakPeriod;

import java.util.ArrayList;
import java.util.List;

public class User {
    private List<Mod> bookmarks;

    // List to store the user's timetables
    private List<Schedule> timetables;

    // Constructor to initialize the user's mods and timetables
    public User() {
        this.timetables = new ArrayList<>();
        this.bookmarks = new ArrayList<>();
    }

    // Method to add a new timetable for the user
    public void addTimetable(String timetableName) {
        for (Schedule timetable : timetables) {
            if (timetable.getName().equalsIgnoreCase(timetableName)) {
                System.out.println("Timetable '" + timetableName + "' already exists.");
                return;
            }
        }
        Schedule newtimetable = new Schedule(timetableName);
        timetables.add(newtimetable);
        System.out.println("Timetable '" + timetableName + "' created successfully.");
    }

    // Method to display the user's mods and timetables
    public Schedule getTimetable(String timetableName) {
        for (Schedule timetable : timetables) {
            if (timetable.getName().equalsIgnoreCase(timetableName)) {
                return timetable;
            }
        }
        System.out.println("Timetable '" + timetableName + "' not found.");
        return null;
    }

    public void setLessonOnTimetable(String timetableName, Mod mod, String type, String id) {
        for (Schedule timetable : timetables) {
            if (timetable.getName().equalsIgnoreCase(timetableName)) {
                timetable.setModLesson(mod, type, id);
            }
        }
        // TODO: throw appropriate error
    }

    public void addModToTimetable(String timetableName, Mod mod) {
        for (Schedule timetable : timetables) {
            if (timetable.getName().equalsIgnoreCase(timetableName)) {
                timetable.addMod(mod);
                return;
            }
        }
        System.out.println("Timetable '" + timetableName + "' not found.");
    }

    public boolean hasTimetable(String inputTimetableName) {
        for (Schedule timetable : timetables) {
            if (timetable.getName().equalsIgnoreCase(inputTimetableName)) {
                return true;
            }
        }
        return false;
    }

    public void removeModFromTimetable(String timetableName, Mod mod) {
        for (Schedule timetable : timetables) {
            if (timetable.getName().equalsIgnoreCase(timetableName)) {
                timetable.removeMod(mod);
                return;
            }
        }
        System.out.println("Timetable '" + timetableName + "' not found.");
    }

    public boolean addBreakToTimetable(String timetableName, BreakPeriod breakPeriod) {
        for (Schedule timetable : timetables) {
            if (timetable.getName().equalsIgnoreCase(timetableName)) {
                timetable.addBreak(breakPeriod);
                return true;
            }
        }
        return false;
    }


    public List<Mod> getBookmarks() {
        return bookmarks;
    }

    public void addBookmark(Mod toAdd) {
        if (!bookmarks.contains(toAdd)) {
            bookmarks.add(toAdd);
        }
    }

    public boolean hasBookmark(Mod toCheck) {
        return bookmarks.contains(toCheck);
    }

    public void removeBookmark(Mod toRemove) {
        bookmarks.remove(toRemove);
    }


}
