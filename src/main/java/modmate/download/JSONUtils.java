package modmate.download;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONUtils {

    private final JSONObject jsonObject;

    public JSONUtils(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public String getString(JSONKey key) {
        return this.jsonObject.getString(key.toString());
    }

    public int getInt(JSONKey key) {
        return this.jsonObject.getInt(key.toString());
    }

    public JSONArray getJSONArray(JSONKey key) {
        return this.jsonObject.getJSONArray(key.toString());
    }

}
