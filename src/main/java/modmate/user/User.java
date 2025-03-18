package modmate.user;

import modmate.mod.Mod;

import java.util.ArrayList;
import java.util.List;

public class User {
    private List<Mod> bookmarks;

    // List to store the user's timetables
    private List<Timetable> timetables;

    // Constructor to initialize the user's mods and timetables
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
    }

    // Method to display the user's mods and timetables
    public String getTimetable(String timetableName) {
        for (Timetable timetable : timetables) {
            if (timetable.getName().equalsIgnoreCase(timetableName)) {
                return timetable.toString();
            }
        }
        return "Timetable '" + timetableName + "' not found";
    }

    public void addModToTimetable(String timetableName, Mod mod) {
        for (Timetable timetable : timetables) {
            if (timetable.getName().equalsIgnoreCase(timetableName)) {
                timetable.addMod(mod);
                return;
            }
        }
        System.out.println("Timetable '" + timetableName + "' not found.");
    }

    public boolean hasTimetable(String inputTimetableName) {
        for (Timetable timetable : timetables) {
            if (timetable.getName().equalsIgnoreCase(inputTimetableName)) {
                return true;
            }
        }
        return false;
    }

    public void removeModFromTimetable(String timetableName, Mod mod) {
        for (Timetable timetable : timetables) {
            if (timetable.getName().equalsIgnoreCase(timetableName)) {
                timetable.removeMod(mod);
                return;
            }
        }
        System.out.println("Timetable '" + timetableName + "' not found.");
    }

    public List<Mod> getBookmarks() {
        return bookmarks;
    }

    public void addBookmark(Mod toAdd) {
        if (!bookmarks.contains(toAdd)) {
            bookmarks.add(toAdd);
        }
    }

    public void removeBookmark(Mod toRemove) {
        bookmarks.remove(toRemove);
    }


}
