package modmate.user;

import modmate.mod.Mod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScheduleTest {
    private Schedule timetable;
    private Mod mod1;
    private Mod mod2;

    @BeforeEach
    void setUp() {
        timetable = new Schedule ("Semester 1");
        mod1 = new Mod("Programming Methodology", "CS1010", null, null, null);
        mod2 = new Mod("Software Engineering & Object-Oriented Programming", "CS2113", null, null, null);
    }

    @Test
    void testAddMod() {
        timetable.addMod(mod1);
        assertEquals(1, timetable.getMods().size()); //timetable should contain 1 mod
        timetable.addMod(mod2);
        assertEquals(2, timetable.getMods().size()); //timetable should contain 2 mods
    }

    @Test
    void testAddDuplicateMod() {
        timetable.addMod(mod1);
        timetable.addMod(mod1); //adding the same mod again
        assertEquals(1, timetable.getMods().size()); //timetable should still contain only 1 mod
    }

    @Test
    void testRemoveExistingMod() {
        timetable.addMod(mod1);
        timetable.removeMod(mod1);
        assertEquals(0, timetable.getMods().size()); //timetable should contain 0 mods
    }

    @Test
    void testRemoveNonExistentMod() {
        timetable.addMod(mod1);
        timetable.removeMod(mod2);
        assertEquals(1, timetable.getMods().size()); //timetable should still contain 1 mod
    }

    @Test
    void testRemoveModFromEmptyList() {
        timetable.removeMod(mod1);
        assertEquals(0, timetable.getMods().size());
    }

    @Test
    void testGetName() {
        assertEquals("Semester 1", timetable.getName());
    }

    @Test
    void testGetMods() {
        timetable.addMod(mod1);
        timetable.addMod(mod2);

        List<Mod> mods = timetable.getMods();
        assertEquals(2, timetable.getMods().size());
        assertEquals(mod1, mods.get(0));
        assertEquals(mod2, mods.get(1));
    }

    @Test
    void testToString() {
        timetable.addMod(mod1);
        timetable.addMod(mod2);

        String expected = "Timetable: Semester 1\n"
                + "  CS1010: Programming Methodology\n"
                + "      null\n"
                + "      No Faculty Information\n"
                + "  CS2113: Software Engineering & Object-Oriented Programming\n"
                + "      null\n"
                + "      No Faculty Information\n";

        System.out.println("Actual Output:\n" + timetable.toString()); // Debugging

        assertEquals(expected, timetable.toString(), "Timetable toString() output does not match.");
    }

}
