package modmate.download.json.timetable;

import modmate.download.json.JSONKey;

public enum TimetableJSONKey implements JSONKey {

    SEMESTER("semester"),
    EXAM_INSTANT("examDate"),
    EXAM_DURATION("examDuration"),
    LESSONS("timetable");

    private final String text;

    TimetableJSONKey(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }

}
