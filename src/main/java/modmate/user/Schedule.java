package modmate.user;

import modmate.mod.Mod;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private String name; // Name of the timetable
    private List<Mod> mods; // List of mods in this timetable

    /**
     * Constructs a new Timetable with the given name.
     *
     * @param name The name of the timetable.
     */
    public Schedule(String name) {
        this.name = name;
        this.mods = new ArrayList<>();
    }

    public void addMod(Mod mod) {
        if (mods.contains(mod)) {
            System.out.println("Mod " + mod.getCode() + " is already in the timetable.");
            return;
        }
        mods.add(mod);
        System.out.println("Mod " + mod.getCode() + " added successfully to " + name + ".");
    }

    public void removeMod(Mod mod) {
        if (!mods.contains(mod)) {
            System.out.println("Mod " + mod.getCode() + " is not in the timetable.");
            return;
        }
        mods.remove(mod);
        System.out.println("Mod " + mod.getCode() + " removed successfully from " + name + ".");
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
     * Returns the list of mods in the timetable.
     *
     * @return The list of mods.
     */
    public List<Mod> getMods() {
        return mods;
    }

    @Override
    public String toString() {
        String result = "Timetable: " + name + "\n";
        if (mods.isEmpty()) {
            result += "  No mods added yet.\n";
        } else {
            for (Mod mod : mods) {
                result += "  " + mod.toString().replace("\n", "\n  ") + "\n";
            }
        }
        return result;
    }
}
