package modmate.download.json.timetable.lesson;

import modmate.download.json.JSONKey;

public enum LessonJSONKey implements JSONKey {

    ID("classNo"),
    START("startTime"),
    END("endTime"),
    WEEKS("weeks"),
    VENUE("venue"),
    DAY("day"),
    LESSON_TYPE("lessonType");

    private final String text;

    LessonJSONKey(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }

}
