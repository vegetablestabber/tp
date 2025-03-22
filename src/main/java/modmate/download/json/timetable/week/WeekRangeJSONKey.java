package modmate.download.json.timetable.week;

import modmate.download.json.JSONKey;

public enum WeekRangeJSONKey implements JSONKey {

    START("start"),
    END("end"),
    WEEKS("weeks"),
    WEEK_INTERVAL("weekInterval");

    private final String text;

    WeekRangeJSONKey(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }

}
