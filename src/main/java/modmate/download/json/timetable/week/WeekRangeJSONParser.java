package modmate.download.json.timetable.week;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

import org.json.JSONArray;
import org.json.JSONObject;

import modmate.download.json.JSONParser;
import modmate.timetable.WeekRange;

public class WeekRangeJSONParser extends JSONParser<WeekRangeJSONKey> {

    public WeekRangeJSONParser(JSONObject jsonObject) {
        super(jsonObject);
    }

    public WeekRange getWeekRange() {
        if (this.has(WeekRangeJSONKey.START)) {
            String startDateString = this.getString(WeekRangeJSONKey.START);
            LocalDate startDate = convertToLocalDate(startDateString);

            String endDateString = this.getString(WeekRangeJSONKey.END);
            LocalDate endDate = convertToLocalDate(endDateString);

            if (this.has(WeekRangeJSONKey.WEEKS)) {
                return new WeekRange(startDate, endDate, getWeeks());
            }

            if (this.has(WeekRangeJSONKey.WEEK_INTERVAL)) {
                int weekInterval = this.getInt(WeekRangeJSONKey.WEEK_INTERVAL);
                return new WeekRange(startDate, endDate, weekInterval);
            }

            return new WeekRange(startDate, endDate);
        }

        return new WeekRange();
    }

    private LocalDate convertToLocalDate(String dateString) {
        return LocalDate.parse(dateString);
    }

    private List<Integer> getWeeks() {
        JSONArray weeksJSONArray = this.getJSONArray(WeekRangeJSONKey.WEEKS);

        List<Integer> weeks = IntStream.range(0, weeksJSONArray.length())
                .map(i -> weeksJSONArray.getInt(i))
                .boxed()
                .toList();

        return weeks;
    }

}
