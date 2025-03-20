package modmate.download.json;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import modmate.mod.Mod;
import modmate.mod.attribute.Faculty;
import modmate.mod.attribute.ModAttributes;
import modmate.mod.attribute.Semester;
import modmate.mod.attribute.WeeklyWorkload;

/**
 * ModJSONParser is responsible for reading and parsing JSON data to create Mod
 * objects.
 */
public class ModJSONParser {

    private final ModJSONUtil jsonUtil;

    /**
     * Constructs a ModJSONParser with the given JSONObject.
     *
     * @param jsonObject the JSONObject to read from
     */
    public ModJSONParser(JSONObject jsonObject) {
        this.jsonUtil = new ModJSONUtil(jsonObject);
    }

    /**
     * Retrieves a Mod object from the JSON data.
     *
     * @return a Mod object
     * @throws JSONException if there is an error parsing the JSON data
     */
    public Mod getModule() throws JSONException {
        return new Mod(
                getName(),
                getCode(),
                getDescription(),
                getModAttributes());
    }

    /**
     * Retrieves the name of the module from the JSON data.
     *
     * @return the name of the module
     * @throws JSONException if there is an error parsing the JSON data
     */
    public String getName() throws JSONException {
        return this.jsonUtil.getString(ModJSONKey.NAME);
    }

    /**
     * Retrieves the code of the module from the JSON data.
     *
     * @return the code of the module
     * @throws JSONException if there is an error parsing the JSON data
     */
    public String getCode() throws JSONException {
        return this.jsonUtil.getString(ModJSONKey.CODE);
    }

    /**
     * Retrieves the description of the module from the JSON data.
     *
     * @return the description of the module
     * @throws JSONException if there is an error parsing the JSON data
     */
    private String getDescription() throws JSONException {
        return this.jsonUtil.getString(ModJSONKey.DESCRIPTION);
    }

    /**
     * Retrieves the attributes of the module from the JSON data.
     *
     * @return the attributes of the module
     * @throws JSONException if there is an error parsing the JSON data
     */
    private ModAttributes getModAttributes() throws JSONException {
        return new ModAttributes(
                getFaculty(),
                getAvailableSemesters(),
                getUnits(),
                getIsGraded(),
                getPrerequisites(),
                getWorkload());
    }

    /**
     * Retrieves the faculty of the module from the JSON data.
     *
     * @return the faculty of the module
     * @throws JSONException if there is an error parsing the JSON data
     */
    private Faculty getFaculty() throws JSONException {
        return new Faculty(this.jsonUtil.getString(ModJSONKey.FACULTY));
    }

    /**
     * Retrieves the available semesters of the module from the JSON data.
     *
     * @return a list of available semesters
     * @throws JSONException if there is an error parsing the JSON data
     */
    private List<Semester> getAvailableSemesters() throws JSONException {
        return this.jsonUtil.getJSONArray(ModJSONKey.AVAILABLE_SEMESTERS)
                .toList()
                .stream()
                .filter(obj -> obj instanceof Map) // Ensure obj is a Map
                .map(obj -> (Map<?, ?>) obj) // Cast obj to Map
                .map(map -> {
                    Object semesterObj = map.get("semester");

                    // Map the semester number to the corresponding enum
                    if (semesterObj instanceof Integer) {
                        return Semester.fromInt((Integer) semesterObj);
                    }

                    throw new IllegalArgumentException(
                            "Invalid semester format: " + semesterObj);
                })
                .toList();
    }

    /**
     * Retrieves the number of units of the module from the JSON data.
     *
     * @return the number of units
     * @throws JSONException if there is an error parsing the JSON data
     */
    private int getUnits() throws JSONException {
        return this.jsonUtil.getInt(ModJSONKey.UNITS);
    }

    /**
     * Retrieves the prerequisites of the module from the JSON data.
     *
     * @return a list of prerequisite modules
     * @throws JSONException if there is an error parsing the JSON data
     */
    private List<Mod> getPrerequisites() throws JSONException {
        return Collections.emptyList();
    }

    /**
     * Checks if the module is graded from the JSON data.
     *
     * @return true if the module is graded, false otherwise
     * @throws JSONException if there is an error parsing the JSON data
     */
    private boolean getIsGraded() throws JSONException {
        return this.jsonUtil.getString(ModJSONKey.IS_GRADED).equals("Graded");
    }

    /**
     * Retrieves the weekly workload of the module from the JSON data.
     *
     * @return the weekly workload
     * @throws JSONException if there is an error parsing the JSON data
     */
    private WeeklyWorkload getWorkload() throws JSONException {
        JSONArray workloadJSONArray = this.jsonUtil.getJSONArray(ModJSONKey.WORKLOAD);

        return new WeeklyWorkload(
                workloadJSONArray.getDouble(0),
                workloadJSONArray.getDouble(1),
                workloadJSONArray.getDouble(3),
                workloadJSONArray.getDouble(4));
    }

}
