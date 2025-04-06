package modmate.download.json.mod.attribute;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;

import modmate.download.json.JSONParser;
import modmate.mod.Mod;
import modmate.mod.attribute.Faculty;
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
        Faculty faculty = new Faculty(this.getString(ModAttrJSONKey.FACULTY));
        double units = this.getDouble(ModAttrJSONKey.UNITS);
        boolean isGraded = this.getString(ModAttrJSONKey.IS_GRADED).equals("Graded");
        Optional<String> workload = this.workloadJSONParser.getWorkload();

        List<Mod> prerequisites = Collections.emptyList();

        return new ModAttributes(faculty, this.availableSemesters, units,
                isGraded, prerequisites, workload);
    }



}
