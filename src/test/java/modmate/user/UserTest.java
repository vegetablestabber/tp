package modmate.user;

import modmate.mod.Mod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class UserTest {
    private User user;
    private Mod mod1;
    private Mod mod2;

    @BeforeEach
    public void setUp() {
        user = new User();
        mod1 = new Mod("Programming Methodology", "CS1010", null, null, null);
        mod2 = new Mod("Software Engineering & Object-Oriented Programming", "CS2113", null, null, null);
    }

    @Test
    void testAddTimetable() {
        String output = getConsoleOutput(() -> user.addTimetable("Semester 1"));
        assertEquals("Timetable 'Semester 1' created successfully.", output);
        assertTrue(user.hasTimetable("Semester 1"));
    }

    @Test
    void testAddDuplicateTimetable() {
        user.addTimetable("Semester 1");
        String output = getConsoleOutput(() -> user.addTimetable("Semester 1")); // Should not create a duplicate
        assertEquals("Timetable 'Semester 1' already exists.", output);
        assertTrue(user.hasTimetable("Semester 1"));
    }

    @Test
    void testGetTimetable() {
        user.addTimetable("Semester 1");
        String timetable = user.getTimetable("Semester 1");
        assertEquals("Timetable: Semester 1\n  No mods added yet.\n", timetable);
    }

    @Test
    void testGetNonExistentTimetable() {
        String timetable = user.getTimetable("Semester 1");
        assertEquals("Timetable 'Semester 1' not found.", timetable);
    }

    @Test
    void testAddModToTimetable() {
        user.addTimetable("Semester 1");
        String output = getConsoleOutput(() -> user.addModToTimetable("Semester 1", mod1));
        assertEquals("Mod CS1010 added successfully to Semester 1.", output);
        assertTrue(user.getTimetable("Semester 1").contains("CS1010"));
    }

    @Test
    void testAddModToNonExistentTimetable() {
        String output = getConsoleOutput(() -> user.addModToTimetable("Semester 1", mod1));
        assertEquals("Timetable 'Semester 1' not found.", output);
    }

    @Test
    void testRemoveModFromTimetable() {
        user.addTimetable("Semester 1");
        user.addModToTimetable("Semester 1", mod1);
        String output = getConsoleOutput(() -> user.removeModFromTimetable("Semester 1", mod1));
        assertEquals("Mod CS1010 removed successfully from Semester 1.", output);
        assertFalse(user.getTimetable("Semester 1").contains("CS1010"));
    }

    @Test
    void testRemoveModFromNonExistentTimetable() {
        String output = getConsoleOutput(() -> user.removeModFromTimetable("Semester 1", mod1));
        assertEquals("Timetable 'Semester 1' not found.", output);
    }

    @Test
    void testBookmarkOperations() {
        //Test adding bookmark
        user.addBookmark(mod1);
        assertEquals(1, user.getBookmarks().size());
        assertTrue(user.getBookmarks().contains(mod1));

        //Test duplicate bookmark
        user.addBookmark(mod1);
        assertEquals(1, user.getBookmarks().size());

        //Test removing bookmark
        user.removeBookmark(mod1);
        assertEquals(0, user.getBookmarks().size());
    }

    @Test
    void testMultipleTimetables() {
        user.addTimetable("Semester 1");
        user.addTimetable("Semester 2");

        user.addModToTimetable("Semester 1", mod1);
        user.addModToTimetable("Semester 2", mod2);

        assertTrue(user.getTimetable("Semester 1").contains("CS1010"));
        assertFalse(user.getTimetable("Semester 1").contains("CS2113"));

        assertTrue(user.getTimetable("Semester 2").contains("CS2113"));
        assertFalse(user.getTimetable("Semester 2").contains("CS1010"));
    }

    private String getConsoleOutput(Runnable code) {
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        code.run();
        System.setOut(System.out);
        return out.toString().trim();
    }
}
