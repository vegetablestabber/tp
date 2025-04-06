package modmate.mod;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modmate.mod.attribute.Faculty;
import modmate.mod.attribute.ModAttributes;
import modmate.mod.attribute.WeeklyWorkload;
import modmate.timetable.Semester;
import modmate.timetable.Timetable;

public class ModTest {
    private Mod mod;
    private ModAttributes attributes;
    private List<Timetable> timetables;

    @BeforeEach
    void setUp() {
        Faculty faculty = new Faculty("Computing");

        WeeklyWorkload workload = new WeeklyWorkload(2, 1, 3, 4);

        attributes = new ModAttributes(faculty, List.of(Semester.SEMESTER_1, Semester.SEMESTER_2), 4,
                true, List.of(), workload);

        timetables = List.of();
        mod = new Mod("Software Engineering & Object-Oriented Programming", "CS2113", null, attributes, timetables);
    }

    @Test
    void testToString() {
        String expected = "CS2113: Software Engineering & Object-Oriented Programming\n" +
                "    null\n" +
                "    Computing";
        assertEquals(expected, mod.toString());
    }

    @Test
    void testGetDetailedString() {
        String result = mod.getDetailedString();
        System.out.println("=== getDetailedString() OUTPUT ===");
        System.out.println(result);
        System.out.println("===================================");

        assertTrue(result.contains("Code: CS2113"));
        assertTrue(result.contains("Name: Software Engineering & Object-Oriented Programming"));
        assertTrue(result.contains("Faculty: Computing"));
        assertTrue(result.contains("Availability: Semester 1, Semester 2"));
        assertTrue(result.contains("Units: 4"));
        assertTrue(result.contains("Graded: true"));
        assertTrue(result.contains("Prerequisites: []"));
        assertTrue(result.contains("Workload: Lectures: 2.0 hours, Tutorials: 1.0 hours, " +
                "Projects: 3.0 hours, Preparation: 4.0 hours"));
    }

    @Test
    void testNullDescription() {
        assertTrue(mod.toString().contains("null"));
    }

    @Test
    void testEmptyTimetables() {
        assertTrue(mod.getTimetables().isEmpty());
    }

    @Test
    void testAttributeAccess() {
        assertEquals("Computing", mod.getAttributes().getFaculty().getName());
        assertEquals(4, mod.getAttributes().getUnits());
    }
}
