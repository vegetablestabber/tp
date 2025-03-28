package modmate.download.json.timetable;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.IntStream;
import java.util.Collections;

import org.json.JSONArray;
import org.json.JSONObject;

import modmate.download.json.JSONParser;
import modmate.download.json.timetable.lesson.LessonJSONParser;
import modmate.timetable.Lesson;
import modmate.timetable.Semester;
import modmate.timetable.Timetable;

public class TimetableJSONParser extends JSONParser<TimetableJSONKey> {

    public TimetableJSONParser(JSONObject jsonObject) {
        super(jsonObject);
    }

    public Timetable getTimetable() {
        Semester semester = Semester.fromInt(getInt(TimetableJSONKey.SEMESTER));

        if (this.has(TimetableJSONKey.EXAM_INSTANT)) {
            Instant examInstant = Instant.parse(getString(TimetableJSONKey.EXAM_INSTANT));
            Duration examDuration = Duration.ofMinutes(getInt(TimetableJSONKey.EXAM_DURATION));

            return new Timetable(semester, examInstant, examDuration, getLessons(), Collections.emptyList());
        }

        return new Timetable(semester, getLessons(),Collections.emptyList());
    }

    private List<Lesson> getLessons() {
        JSONArray lessonJSONArray = this.getJSONArray(TimetableJSONKey.LESSONS);

        return IntStream.range(0, lessonJSONArray.length())
            .mapToObj(i -> lessonJSONArray.getJSONObject(i))
            .map(jsonObject -> {
                LessonJSONParser lessonJSONParser = new LessonJSONParser(jsonObject);
                return lessonJSONParser.getLesson();
            })
            .toList();
    }

}
