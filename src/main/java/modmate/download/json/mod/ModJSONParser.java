package modmate.download.json.mod;

import java.util.List;
import java.util.stream.IntStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import modmate.download.json.JSONParser;
import modmate.download.json.mod.attribute.ModAttrJSONParser;
import modmate.download.json.timetable.TimetableJSONParser;
import modmate.log.Log;
import modmate.mod.Mod;
import modmate.mod.attribute.ModAttributes;
import modmate.timetable.Semester;
import modmate.timetable.Timetable;

/**
 * ModJSONParser is responsible for reading and parsing JSON data to create Mod
 * objects.
 */
public class ModJSONParser extends JSONParser<ModJSONKey> {

    private final List<Timetable> timetables;
    private final ModAttrJSONParser modAttrJSONParser;

    /**
     * Constructs a ModJSONParser with the given JSONObject.
     *
     * @param jsonObject the JSONObject to read from
     */
    public ModJSONParser(JSONObject jsonObject) {
        super(jsonObject);
        this.timetables = getTimetables();

        List<Semester> availableSemesters = this.timetables.stream()
                .map(timetable -> timetable.getSemester())
                .toList();
        this.modAttrJSONParser = new ModAttrJSONParser(jsonObject, availableSemesters);
    }

    /**
     * Retrieves a Mod object from the JSON data.
     *
     * @return a Mod object
     * @throws JSONException if there is an error parsing the JSON data
     */
    public Mod getModule() throws JSONException {
        String name = this.getString(ModJSONKey.NAME);
        String code = this.getString(ModJSONKey.CODE);
        String description = this.getString(ModJSONKey.DESCRIPTION);
        ModAttributes attributes = modAttrJSONParser.getAttributes();

        attributes.getWorkload()
                .ifPresentOrElse(
                    w -> {},
                    () -> Log.saveLog("[MODATTRJSONPARSER]   Mod "
                            + code + "doesn't have workload.")
                );

        return new Mod(name, code, description, attributes, timetables);
    }

    private List<Timetable> getTimetables() {
        JSONArray timetableJSONArray = this.getJSONArray(ModJSONKey.TIMETABLES);

        return IntStream.range(0, timetableJSONArray.length())
                .mapToObj(i -> timetableJSONArray.getJSONObject(i))
                .map(jsonObject -> {
                    TimetableJSONParser timetableJSONParser = new TimetableJSONParser(jsonObject);
                    return timetableJSONParser.getTimetable();
                })
                .toList();
    }

}
