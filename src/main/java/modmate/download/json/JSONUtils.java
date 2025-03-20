package modmate.download.json;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONUtils {

    private final JSONObject jsonObject;

    public JSONUtils(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public String getString(ModJSONKey key) {
        return this.jsonObject.getString(key.toString());
    }

    public int getInt(ModJSONKey key) {
        return this.jsonObject.getInt(key.toString());
    }

    public JSONArray getJSONArray(ModJSONKey key) {
        return this.jsonObject.getJSONArray(key.toString());
    }

}
