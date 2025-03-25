package modmate.mod;

import java.util.List;

import modmate.mod.attribute.ModAttributes;
import modmate.timetable.Timetable;

/**
 * Represents a mod with a specific name, code, description, and associated
 * attributes.
 * This class stores the name, code, description, and attributes of the mod
 * and provides methods to access each of these properties.
 */
public class Mod extends CondensedMod {

    /**
     * A brief description of the mod.
     */
    private final String description;

    /**
     * A list of timetables associated with the module.
     */
    private final List<Timetable> timetables;

    /**
     * The attributes associated with the mod, such as mod level, credits, etc.
     */
    private final ModAttributes attributes;

    /**
     * Constructs a new Mod object with the specified name, code, description, and
     * attributes.
     *
     * @param name        The name of the mod.
     * @param code        The code of the mod.
     * @param description A brief description of the mod.
     * @param timetables  A list of timetables associated with the module.
     * @param attributes  The attributes associated with the mod.
     */
    public Mod(String name, String code, String description,
            ModAttributes attributes, List<Timetable> timetables) {
        super(name, code);
        this.description = description;
        this.timetables = timetables;
        this.attributes = attributes;
    }

    /**
     * Returns a string representation of the mod.
     *
     * @return A string representation of the mod.
     */
    @Override
    public String toString() {
        return code + ": " + name + "\n    " + description + "\n    " +
                (attributes != null ? attributes.getFaculty() : "No Faculty Info");
    }

    /**
     * Returns a detailed string representation of the mod.
     *
     * @return A detailed string representation of the mod.
     */
    public String getDetailedString() {
        return "Code: " + code +
                "\nName: " + name +
                "\nDescription: " + description +
                "\nFaculty: " + attributes.getFaculty() +
                "\nAvailability: " + attributes.listAvailableSemesters() +
                "\nUnits: " + attributes.getUnits() +
                "\nGraded: " + attributes.isGraded() +
                "\nPrerequisites: " + attributes.getPrerequisites() +
                "\nWorkload: " + attributes.getWorkload();
    }

    /**
     * Returns the description of the mod.
     *
     * @return The description of the mod.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the list of timetables associated with the mod.
     *
     * @return The list of timetables associated with the mod.
     */
    public List<Timetable> getTimetables() {
        return timetables;
    }

    /**
     * Returns the attributes associated with the mod.
     *
     * @return The attributes associated with the mod.
     */
    public ModAttributes getAttributes() {
        return attributes;
    }

}
