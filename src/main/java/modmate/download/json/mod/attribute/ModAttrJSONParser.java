package modmate.download.json.mod.attribute;

import java.util.List;

import org.json.JSONObject;

import modmate.download.json.JSONParser;
import modmate.mod.attribute.ModAttributes;
import modmate.timetable.Semester;

public class ModAttrJSONParser extends JSONParser<ModAttrJSONKey> {

    private final List<Semester> availableSemesters;
    private final WorkloadJSONParser workloadJSONParser;

    public ModAttrJSONParser(JSONObject jsonObject, List<Semester> semesters) {
        super(jsonObject);
        this.availableSemesters = semesters;
        this.workloadJSONParser = new WorkloadJSONParser(jsonObject);
    }

    public ModAttributes getAttributes() {
        return new ModAttributes(
            this.getString(ModAttrJSONKey.FACULTY),
            this.availableSemesters,
            this.getDouble(ModAttrJSONKey.UNITS) + "",
            this.getString(ModAttrJSONKey.IS_GRADED).equals("Graded") + "",
            this.workloadJSONParser.getWorkload()
        );
    }

}
