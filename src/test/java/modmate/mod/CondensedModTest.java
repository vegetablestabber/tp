package modmate.mod;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CondensedModTest {

    @Test
    void testConstructorAndGetter() {
        CondensedMod mod = new CondensedMod("Programming Methodology", "CS1010");

        assertEquals("Programming Methodology", mod.getName());
        assertEquals("CS1010", mod.getCode());
    }

    @Test
    void testNullName() {
        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> new CondensedMod(null, "CS1010"));
        assertEquals("Name is null", exception.getMessage());
    }

    @Test
    void testNullCode() {
        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> new CondensedMod("Programming Methodology", null));
        assertEquals("Code is null", exception.getMessage());
    }
}
