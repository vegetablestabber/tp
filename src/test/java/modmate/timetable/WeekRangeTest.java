package modmate.timetable;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WeekRangeTest {

    @Test
    void testDefaultConstructor() {
        WeekRange range = new WeekRange();
        assertEquals(13, range.getWeeks().size());
        assertTrue(range.getStartDate().isEmpty());
        assertTrue(range.getEndDate().isEmpty());
    }

    @Test
    void testConstructorWithStartEnd() {
        LocalDate start = LocalDate.of(2024, 1, 8);
        LocalDate end = LocalDate.of(2024, 4, 8);
        WeekRange range = new WeekRange(start, end);

        assertEquals(start, range.getStartDate().get());
        assertEquals(end, range.getEndDate().get());
    }

    @Test
    void testToStringWithGaps() {
        WeekRange custom = new WeekRange(
                LocalDate.now(),
                LocalDate.now().plusWeeks(13),
                Arrays.asList(1, 2, 3, 5, 7, 8, 10));
        assertEquals("1–3, 5, 7–8, 10", custom.toString());
    }

    @Test
    void testToStringWithEmptyList() {
        WeekRange empty = new WeekRange(LocalDate.now(), LocalDate.now(), Arrays.asList());
        assertEquals("", empty.toString());
    }
}
