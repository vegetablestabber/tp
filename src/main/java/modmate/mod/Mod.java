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
     * Returns a detailed string representation of the mod.
     *
     * @return A detailed string representation of the mod.
     */
    public String getDetailedString() {
        return "Code: " + code +
                "\nName: " + name +
                "\nDescription: " + description +
                "\n" + attributes.toString();
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

    /**
     * Returns equality of mod with another object. Mod equality
     * is determined by whether the mods have the same code or not
     * @param o object to test
     * @return if the mod is the same (That is, occupies the same code)
     */
    public boolean equals(Object o) {
        return o instanceof Mod && ((Mod) o).getCode().equals(code);
    }
}
