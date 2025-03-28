package modmate.download.json.mod;

import org.json.JSONException;
import org.json.JSONObject;

import modmate.download.json.JSONParser;
import modmate.mod.CondensedMod;

/**
 * ModJSONParser is responsible for reading and parsing JSON data to create
 * CondensedMod objects.
 */
public class CondensedModJSONParser extends JSONParser<CondensedModJSONKey> {

    /**
     * Constructs a CondensedModJSONParser with the given JSONObject.
     *
     * @param jsonObject the JSONObject to read from
     */
    public CondensedModJSONParser(JSONObject jsonObject) {
        super(jsonObject);
    }

    /**
     * Retrieves a CondensedMod object from the JSON data.
     *
     * @return a CondensedMod object
     * @throws JSONException if there is an error parsing the JSON data
     */
    public CondensedMod getModule() throws JSONException {
        String name = this.getString(CondensedModJSONKey.NAME);
        String code = this.getString(CondensedModJSONKey.CODE).toUpperCase();

        return new CondensedMod(name, code);
    }

}
