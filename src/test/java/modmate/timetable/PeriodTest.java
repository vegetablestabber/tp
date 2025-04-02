package modmate.timetable;

import org.junit.jupiter.api.Test;
import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class PeriodTest {

    @Test
    void testConstructorAndGetters() {
        WeekRange range = new WeekRange();
        Period period = new Period(DayOfWeek.THURSDAY, LocalTime.of(13, 0), LocalTime.of(14, 0), range);

        assertEquals(DayOfWeek.THURSDAY, period.getDay());
        assertEquals(LocalTime.of(13, 0), period.getStartTime());
        assertEquals(LocalTime.of(14, 0), period.getEndTime());
        assertEquals(range, period.getWeekRange());
    }

    @Test
    void testGetDuration() {
        Period period = new Period(DayOfWeek.TUESDAY, LocalTime.of(9, 0), LocalTime.of(10, 30));
        assertEquals(90, period.getDuration());
    }

    @Test
    void testToString() {
        Period period = new Period(DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(9, 0));
        String output = period.toString();
        assertTrue(output.contains("MONDAY"));
        assertTrue(output.contains("08:00"));
    }
}
