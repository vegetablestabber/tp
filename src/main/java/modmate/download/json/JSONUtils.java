package modmate.download.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONUtils {

    private final JSONObject jsonObject;

    public JSONUtils(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public String getString(ModJSONKey key) throws JSONException {
        return this.jsonObject.getString(key.toString());
    }

    public int getInt(ModJSONKey key) throws JSONException {
        return this.jsonObject.getInt(key.toString());
    }

    public JSONArray getJSONArray(ModJSONKey key) throws JSONException {
        return this.jsonObject.getJSONArray(key.toString());
    }

}
