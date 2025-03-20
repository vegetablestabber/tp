package modmate.download.json;

/**
 * Enum representing the keys used in JSON data for module information.
 */
public enum ModJSONKey {

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
     * The faculty offering the module.
     */
    FACULTY("faculty"),

    /**
     * The semesters in which the module is available.
     */
    AVAILABLE_SEMESTERS("semesterData"),

    /**
     * The number of units or credits for the module.
     */
    UNITS("moduleCredit"),

    // TODO: Need one for prerequisites

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
