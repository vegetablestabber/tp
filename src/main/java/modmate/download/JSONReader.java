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

public class JSONReader {

    private final JSONUtils jsonUtils;

    public JSONReader(JSONObject jsonObject) {
        this.jsonUtils = new JSONUtils(jsonObject);
    }

    public Mod getModule() {
        return new Mod(
                getName(),
                getCode(),
                getDescription(),
                getModAttributes());
    }

    private String getName() {
        return this.jsonUtils.getString(JSONKey.NAME);
    }

    private String getCode() {
        return this.jsonUtils.getString(JSONKey.CODE);
    }

    private String getDescription() {
        return this.jsonUtils.getString(JSONKey.DESCRIPTION);
    }

    private ModAttributes getModAttributes() {
        return new ModAttributes(
                getFaculty(),
                getAvailableSemesters(),
                getUnits(),
                getIsGraded(),
                getPrerequisites(),
                getWorkload());
    }

    private Faculty getFaculty() {
        return new Faculty(this.jsonUtils.getString(JSONKey.FACULTY));
    }

    /*
     * TODO: We could use Jackson, which is a popular JSON parsing library for Java
     * - this would be better for error handling
     * - casting it to a Map directly is error-prone
     * - not changing the current approach because it works now and
     * I don't have enough time to refactor again at the moment
     * (i.e., 'if it ain't broke, don't fix it')
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

    private int getUnits() {
        return this.jsonUtils.getInt(JSONKey.UNITS);
    }

    // TODO: Need to return real prequisites
    private List<Mod> getPrerequisites() {
        return Collections.emptyList();
    }

    private boolean getIsGraded() {
        return this.jsonUtils.getString(JSONKey.IS_GRADED).equals("Graded");
    }

    private WeeklyWorkload getWorkload() {
        JSONArray workloadJSONArray = this.jsonUtils.getJSONArray(JSONKey.WORKLOAD);
        return new WeeklyWorkload(
                workloadJSONArray.getDouble(0),
                workloadJSONArray.getDouble(1),
                workloadJSONArray.getDouble(3),
                workloadJSONArray.getDouble(4));
    }

}
