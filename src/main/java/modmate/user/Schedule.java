package modmate.user;

import modmate.mod.Mod;
import modmate.timetable.BreakPeriod;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private final String name; // Name of the timetable
    private final List<ScheduleMod> mods; // List of mods in this timetable
    private final List<BreakPeriod> breaks = new ArrayList<>();

    /**
     * Constructs a new Timetable with the given name.
     *
     * @param name The name of the timetable.
     */
    public Schedule(String name) {
        this.name = name;
        this.mods = new ArrayList<>();


    }

    public void addMod(Mod modToAdd) {
        for (ScheduleMod existingMod : mods) {
            if (existingMod.getMod().equals(modToAdd)) {
                System.out.println("Mod " + modToAdd.getCode() + " is already in the timetable.");
                return;
            }
        }

        mods.add(new ScheduleMod(modToAdd));
        System.out.println("Mod " + modToAdd.getCode() + " added successfully to " + name + ".");
    }

    public void setModLesson(Mod modToSet, String type, String id) {
        ScheduleMod scheduleMod = mods
                .stream()
                .filter(
                        mod -> mod.getMod().getCode().equalsIgnoreCase(modToSet.getCode()))
                .findFirst()
                .orElse(null);

        if (scheduleMod == null) {
            System.out.println("Mod " + modToSet.getCode() + " is not in the timetable.");
        }

        scheduleMod.setLesson(type, id);
    }

    public void removeMod(Mod modToRemove) {
        for (ScheduleMod existingMod : mods) {
            if (existingMod.getMod().getCode().equalsIgnoreCase(modToRemove.getCode())) {
                mods.remove(existingMod);
                System.out.println("Mod " + modToRemove.getCode() + " removed successfully from " + name + ".");
                return;
            }
        }
        System.out.println("Mod " + modToRemove.getCode() + " is not in the timetable.");
    }

    public void addBreak(BreakPeriod breakPeriod) {
        breaks.add(breakPeriod);
    }

    public List<BreakPeriod> getBreaks() {
        return breaks;
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
        return mods.stream().map(ScheduleMod::getMod).toList();
    }

    public ScheduleMod getModSchedule(Mod modToGet) {
        return mods.stream().filter(mod -> mod.getMod().getCode().equals(modToGet.getCode())).findAny().orElse(null);
    }

    @Override
    public String toString() {
        String result = "Timetable: " + name + "\n";
        if (mods.isEmpty()) {
            result += "  No mods added yet.\n";
        } else {
            for (ScheduleMod mod : mods) {
                result += "  " + mod.toString().replace("\n", "\n  ") + "\n\n";
            }
        }
        if (!breaks.isEmpty()) {
            result += "  Breaks:\n";
            for (BreakPeriod b : breaks) {
                result += "    " + b + "\n";
            }
        }

        return result;
    }
}
