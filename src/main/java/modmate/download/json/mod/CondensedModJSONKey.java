package modmate.download.json.mod;

import modmate.download.json.JSONKey;

/**
 * Enum representing the keys used in JSON data for module information.
 */
public enum CondensedModJSONKey implements JSONKey {

    /**
     * The name of the module.
     */
    NAME("title"),

    /**
     * The code of the module.
     */
    CODE("moduleCode");

    private final String text;

    /**
     * Constructor for ModJSONKey enum.
     *
     * @param text The text representation of the JSON key.
     */
    private CondensedModJSONKey(String text) {
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
