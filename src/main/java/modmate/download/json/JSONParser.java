package modmate.download.json;

import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONParser<K extends JSONKey> {

    private final JSONObject jsonObject;

    public JSONParser(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public boolean has(K key) {
        return this.jsonObject.has(key.toString());
    }

    public String getString(K key) throws JSONException {
        return this.jsonObject.getString(key.toString());
    }

    public int getInt(K key) throws JSONException {
        return this.jsonObject.getInt(key.toString());
    }

    public JSONObject getJSONObject(K key) {
        return this.jsonObject.getJSONObject(key.toString());
    }

    public Optional<JSONObject> optJSONObject(K key) {
        return Optional.ofNullable(this.jsonObject.optJSONObject(key.toString()));
    }

    public JSONArray getJSONArray(JSONKey key) throws JSONException {
        return this.jsonObject.getJSONArray(key.toString());
    }

}
