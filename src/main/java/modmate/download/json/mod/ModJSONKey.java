package modmate.download.json.mod;

import modmate.download.json.JSONKey;

/**
 * Enum representing the keys used in JSON data for module information.
 */

public enum ModJSONKey implements JSONKey {

    /**
     * The name of the module.
     */
    NAME("title"),

    /**
     * The code of the module.
     */
    CODE("moduleCode"),

    /**
     * The description of the module.
     */
    DESCRIPTION("description"),

    /**
     * The number of modular credits (MCs) for the module.
     */
    UNITS("moduleCredit"),

    /**
     * The semester data of the module.
     */
    TIMETABLES("semesterData"),

    /**
     * The prerequisites for the module.
     */
    PREREQUISITES("prereqTree"),

    /**
     * Indicates if the module is graded.
     */
    IS_GRADED("gradingBasisDescription"),

    /**
     * The workload of the module.
     */
    WORKLOAD("workload");

    private final String text;

    /**
     * Constructor for ModJSONKey enum.
     *
     * @param text The text representation of the JSON key.
     */
    private ModJSONKey(String text) {
        this.text = text;
    }

    /**
     * Returns the text representation of the JSON key.
     *
     * @return The text representation of the JSON key.
     */
    @Override
    public String toString() {
        return this.text;
    }

}
