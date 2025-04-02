package modmate.timetable;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LessonTest {

    @Test
    void testConstructorAndGetters() {
        Period period = new Period(DayOfWeek.WEDNESDAY, LocalTime.of(10, 0), LocalTime.of(12, 0));
        Lesson lesson = new Lesson("Lecture", "A01", period, "LT19");

        assertEquals("Lecture", lesson.getType());
        assertEquals("A01", lesson.getId());
        assertEquals(period, lesson.getPeriod());
        assertEquals("LT19", lesson.getVenue());
    }

    @Test
    void testToString() {
        Period period = new Period(DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(10, 0));
        Lesson lesson = new Lesson("Tutorial", "T02", period, "COM1");

        String result = lesson.toString();
        assertTrue(result.contains("[T02]"));
        assertTrue(result.contains("COM1"));
    }

    @Test
    void testCompareTo() {
        Lesson l1 = new Lesson("Lecture", "A01", null, "");
        Lesson l2 = new Lesson("Lecture", "A02", null, "");
        Lesson l3 = new Lesson("Lecture", "B01", null, "");

        assertTrue(l1.compareTo(l2) < 0);
        assertTrue(l2.compareTo(l3) < 0);
        assertTrue(l1.compareTo(l1) == 0);
    }
}
