package modmate.download;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import modmate.mod.Mod;
import modmate.mod.attribute.Faculty;
import modmate.mod.attribute.ModAttributes;
import modmate.mod.attribute.Semester;
import modmate.mod.attribute.WeeklyWorkload;

/**
 * JSONReader is responsible for reading and parsing JSON data to create Mod objects.
 */
public class JSONReader {

    private final JSONUtils jsonUtils;

    /**
     * Constructs a JSONReader with the given JSONObject.
     *
     * @param jsonObject the JSONObject to read from
     */
    public JSONReader(JSONObject jsonObject) {
        this.jsonUtils = new JSONUtils(jsonObject);
    }

    /**
     * Retrieves a Mod object from the JSON data.
     *
     * @return a Mod object
     */
    public Mod getModule() {
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
     */
    private String getName() {
        return this.jsonUtils.getString(JSONKey.NAME);
    }

    /**
     * Retrieves the code of the module from the JSON data.
     *
     * @return the code of the module
     */
    private String getCode() {
        return this.jsonUtils.getString(JSONKey.CODE);
    }

    /**
     * Retrieves the description of the module from the JSON data.
     *
     * @return the description of the module
     */
    private String getDescription() {
        return this.jsonUtils.getString(JSONKey.DESCRIPTION);
    }

    /**
     * Retrieves the attributes of the module from the JSON data.
     *
     * @return the attributes of the module
     */
    private ModAttributes getModAttributes() {
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
     */
    private Faculty getFaculty() {
        return new Faculty(this.jsonUtils.getString(JSONKey.FACULTY));
    }

    /**
     * Retrieves the available semesters of the module from the JSON data.
     *
     * @return a list of available semesters
     */
    private List<Semester> getAvailableSemesters() {
        return this.jsonUtils.getJSONArray(JSONKey.AVAILABLE_SEMESTERS)
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
     */
    private int getUnits() {
        return this.jsonUtils.getInt(JSONKey.UNITS);
    }

    /**
     * Retrieves the prerequisites of the module from the JSON data.
     *
     * @return a list of prerequisite modules
     */
    private List<Mod> getPrerequisites() {
        return Collections.emptyList();
    }

    /**
     * Checks if the module is graded from the JSON data.
     *
     * @return true if the module is graded, false otherwise
     */
    private boolean getIsGraded() {
        return this.jsonUtils.getString(JSONKey.IS_GRADED).equals("Graded");
    }

    /**
     * Retrieves the weekly workload of the module from the JSON data.
     *
     * @return the weekly workload
     */
    private WeeklyWorkload getWorkload() {
        JSONArray workloadJSONArray = this.jsonUtils.getJSONArray(JSONKey.WORKLOAD);
        return new WeeklyWorkload(
                workloadJSONArray.getDouble(0),
                workloadJSONArray.getDouble(1),
                workloadJSONArray.getDouble(3),
                workloadJSONArray.getDouble(4));
    }

}
