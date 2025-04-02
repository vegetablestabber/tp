package modmate.timetable;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TimetableTest {

    private final Semester dummySemester = Semester.SEMESTER_1; // fixed here ✅

    private final Period period = new Period(DayOfWeek.MONDAY,
            LocalTime.of(10, 0), LocalTime.of(12, 0));

    private final Lesson lesson = new Lesson("Lecture", "A01", period, "LT19");
    private final BreakPeriod breakPeriod = new BreakPeriod("Lunch", new Period(DayOfWeek.FRIDAY,
            LocalTime.of(12, 0), LocalTime.of(13, 0)));

    @Test
    void testConstructorWithExam() {
        Instant examTime = Instant.parse("2025-05-05T09:00:00Z");
        Duration duration = Duration.ofMinutes(120);

        Timetable timetable = new Timetable(dummySemester, examTime, duration,
                List.of(lesson), List.of(breakPeriod));

        assertEquals(dummySemester, timetable.getSemester());
        assertEquals(Optional.of(examTime), timetable.getExamInstant());
        assertEquals(Optional.of(duration), timetable.getExamDuration());
        assertEquals(1, timetable.getLessons().size());
    }

    @Test
    void testConstructorWithoutExam() {
        Timetable timetable = new Timetable(dummySemester,
                List.of(lesson), List.of(breakPeriod));

        assertEquals(dummySemester, timetable.getSemester());
        assertTrue(timetable.getExamInstant().isEmpty());
        assertTrue(timetable.getExamDuration().isEmpty());
        assertEquals(1, timetable.getLessons().size());
    }

    @Test
    void testEmptyLessonList() {
        Timetable timetable = new Timetable(dummySemester, List.of(), List.of());
        assertTrue(timetable.getLessons().isEmpty());
    }

    @Test
    void testMultipleLessonsAndBreaks() {
        Lesson l2 = new Lesson("Tutorial", "B01", period, "COM1");
        BreakPeriod b2 = new BreakPeriod("Gym", period);

        Timetable timetable = new Timetable(dummySemester,
                List.of(lesson, l2), List.of(breakPeriod, b2));

        assertEquals(2, timetable.getLessons().size());
    }
}
