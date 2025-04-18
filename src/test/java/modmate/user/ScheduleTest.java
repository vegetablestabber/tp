package modmate.user;

import modmate.mod.Mod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScheduleTest {
    private Schedule schedule;
    private Mod mod1;
    private Mod mod2;

    @BeforeEach
    void setUp() {
        schedule = new Schedule("Semester 1");
        mod1 = new Mod("Programming Methodology", "CS1010", null, null, null);
        mod2 = new Mod("Software Engineering & Object-Oriented Programming", "CS2113", null, null, null);
    }

    @Test
    void testAddMod() {
        String output1 = getConsoleOutput(() -> schedule.addMod(mod1));
        assertEquals(1, schedule.getMods().size()); //timetable should contain 1 mod
        assertEquals("Mod CS1010 added successfully to Semester 1.", output1);

        String output2 = getConsoleOutput(() -> schedule.addMod(mod2));
        assertEquals(2, schedule.getMods().size()); //timetable should contain 2 mods
        assertEquals("Mod CS2113 added successfully to Semester 1.", output2);
    }

    @Test
    void testAddDuplicateMod() {
        schedule.addMod(mod1);
        String output = getConsoleOutput(() -> schedule.addMod(mod1)); //adding the same mod again
        assertEquals(1, schedule.getMods().size()); //timetable should still contain only 1 mod
        assertEquals("Mod CS1010 is already in the timetable.", output);
    }

    @Test
    void testRemoveExistingMod() {
        schedule.addMod(mod1);
        String output = getConsoleOutput(() -> schedule.removeMod(mod1));
        assertEquals(0, schedule.getMods().size()); //timetable should contain 0 mods
        assertEquals("Mod CS1010 removed successfully from Semester 1.", output);
    }

    @Test
    void testRemoveNonExistentMod() {
        schedule.addMod(mod1);
        String output = getConsoleOutput(() -> schedule.removeMod(mod2));
        assertEquals(1, schedule.getMods().size()); //timetable should still contain 1 mod
        assertEquals("Mod CS2113 is not in the timetable.", output);
    }

    @Test
    void testRemoveModFromEmptyList() {
        String output = getConsoleOutput(() -> schedule.removeMod(mod1));
        assertEquals(0, schedule.getMods().size());
        assertEquals("Mod CS1010 is not in the timetable.", output);
    }

    @Test
    void testGetName() {
        assertEquals("Semester 1", schedule.getName());
    }

    @Test
    void testGetModsEmptyList() {
        List<Mod> mods = schedule.getMods();
        assertEquals(0, mods.size());
    }

    @Test
    void testGetModsWithMods() {
        schedule.addMod(mod1);
        schedule.addMod(mod2);

        List<Mod> mods = schedule.getMods();
        assertEquals(2, schedule.getMods().size());
        assertEquals(mod1, mods.get(0));
        assertEquals(mod2, mods.get(1));
    }

    @Test
    void testToString() {
        schedule.addMod(mod1);
        schedule.addMod(mod2);

        String expected = "Timetable: Semester 1\n"
            + "  CS1010: Programming Methodology\n\n"
            + "  CS2113: Software Engineering & Object-Oriented Programming\n\n";

        System.out.println("Actual Output:\n" + schedule.toString()); // Debugging

        assertEquals(expected, schedule.toString());
    }

    private String getConsoleOutput(Runnable code) {
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        code.run();
        System.setOut(System.out);
        return out.toString().trim();
    }
}
