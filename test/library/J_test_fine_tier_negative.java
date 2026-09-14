package library;

import org.junit.Test;
import static org.junit.Assert.assertThrows;

public class J_test_fine_tier_negative {

    @Test
    public void testFineTierNegativeDaysRaises() {
        assertThrows(IllegalArgumentException.class, () -> {
            Library.fineTier(-3);
        });
    }
}
