package modmate.timetable;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BreakPeriodTest {

    @Test
    void testConstructorAndGetters() {
        Period period = new Period(DayOfWeek.MONDAY, LocalTime.of(12, 0), LocalTime.of(13, 0));
        BreakPeriod breakPeriod = new BreakPeriod("Lunch", period);

        assertEquals("Lunch", breakPeriod.getLabel());
        assertEquals(period, breakPeriod.getPeriod());
    }

    @Test
    void testToString() {
        Period period = new Period(DayOfWeek.FRIDAY, LocalTime.of(14, 0), LocalTime.of(15, 0));
        BreakPeriod breakPeriod = new BreakPeriod("Gym", period);

        String expected = "[Gym] FRIDAY: 14:00–15:00";
        assertEquals(expected, breakPeriod.toString());
    }
}
