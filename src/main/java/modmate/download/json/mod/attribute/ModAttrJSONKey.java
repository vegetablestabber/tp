package modmate.download.json.mod.attribute;

import modmate.download.json.JSONKey;

public enum ModAttrJSONKey implements JSONKey {

    /**
     * The faculty offering the module.
     */
    FACULTY("faculty"),

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
     * Constructor for ModAttributesJSONKey enum.
     *
     * @param text The text representation of the JSON key.
     */
    private ModAttrJSONKey(String text) {
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
