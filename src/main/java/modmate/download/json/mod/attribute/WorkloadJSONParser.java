package modmate.download.json.mod.attribute;

import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import modmate.download.json.JSONParser;
import modmate.mod.attribute.WeeklyWorkload;

public class WorkloadJSONParser extends JSONParser<ModAttrJSONKey> {

    public WorkloadJSONParser(JSONObject jsonObject) {
        super(jsonObject);
    }

    /**
     * Retrieves the weekly workload of the module from the JSON data.
     *
     * @return the weekly workload
     * @throws JSONException if there is an error parsing the JSON data
     */
    public Optional<String> getWorkload() throws JSONException {
        ModAttrJSONKey jsonKey = ModAttrJSONKey.WORKLOAD;

        if (this.has(jsonKey)) {
            try {
                JSONArray workloadJSONArray = this.getJSONArray(jsonKey);
                WeeklyWorkload workload = new WeeklyWorkload(
                        workloadJSONArray.getDouble(0),
                        workloadJSONArray.getDouble(1),
                        workloadJSONArray.getDouble(3),
                        workloadJSONArray.getDouble(4));

                return Optional.of(workload.toString());
            } catch (JSONException e) {
                return Optional.of(this.getString(jsonKey));
            }
        }

        return Optional.empty();
    }

}
