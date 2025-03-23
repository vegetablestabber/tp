package modmate.download.json.timetable.lesson;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.json.JSONObject;

import modmate.download.json.JSONParser;
import modmate.download.json.timetable.week.WeekRangeJSONParser;
import modmate.timetable.Lesson;
import modmate.timetable.Period;
import modmate.timetable.WeekRange;

public class LessonJSONParser extends JSONParser<LessonJSONKey> {

    /**
     * Constructs a LessonJSONParser with the given JSONObject.
     *
     * @param jsonObject the JSONObject to read from
     */
    public LessonJSONParser(JSONObject jsonObject) {
        super(jsonObject);
    }

    public Lesson getLesson() {
        String type = this.getString(LessonJSONKey.LESSON_TYPE);
        String id = this.getString(LessonJSONKey.ID);
        String venue = this.getString(LessonJSONKey.VENUE);

        return new Lesson(type, id, getPeriod(), venue);
    }

    private Period getPeriod() {
        String dayString = this.getString(LessonJSONKey.DAY);
        DayOfWeek day = DayOfWeek.valueOf(dayString.toUpperCase());

        String startTimeString = this.getString(LessonJSONKey.START);
        LocalTime startTime = convertToLocalTime(startTimeString);

        String endTimeString = this.getString(LessonJSONKey.END);
        LocalTime endTime = convertToLocalTime(endTimeString);

        if (this.has(LessonJSONKey.WEEKS)) {
            Optional<JSONObject> weeksJSONObject = this.optJSONObject(LessonJSONKey.WEEKS);

            return weeksJSONObject.map(jsonObject -> {
                WeekRangeJSONParser weekJSONParser = new WeekRangeJSONParser(jsonObject);
                WeekRange weekRange = weekJSONParser.getWeekRange();

                return new Period(day, startTime, endTime, weekRange);
            })
            .orElseGet(() -> new Period(day, startTime, endTime));
        }

        return new Period(day, startTime, endTime);
    }

    private LocalTime convertToLocalTime(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        return LocalTime.parse(timeString, formatter);
    }

}
