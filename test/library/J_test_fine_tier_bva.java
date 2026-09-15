package library;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * Boundary Value Analysis suite for Library.fineTier(daysOverdue).
 *
 * Covers all 5 boundaries enumerated in docs/bva-analysis.md (Task 1):
 *   1. Domain edge  : negative / 0      -> -1, 0, 1
 *   2. None / Low   : 0 -> 1            -> 0, 1, 2
 *   3. Low / Medium : 7 -> 8            -> 7, 8, 9
 *   4. Medium / High: 14 -> 15          -> 14, 15, 16
 *   5. High / Severe: 30 -> 31          -> 30, 31, 32
 *
 * expectedTier == null signals that fineTier() is expected to throw
 * IllegalArgumentException (the only invalid point, -1, from boundary 1).
 */
@RunWith(Parameterized.class)
public class J_test_fine_tier_bva {

    private int days;
    private String expectedTier;

    public J_test_fine_tier_bva(int days, String expectedTier) {
        this.days = days;
        this.expectedTier = expectedTier;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            // Boundary 1: domain edge (negative / 0)
            {-1, null},        // invalid -> throws IllegalArgumentException
            {0, "None"},
            {1, "Low"},

            // Boundary 2: None / Low (0 -> 1)
            {0, "None"},
            {1, "Low"},
            {2, "Low"},

            // Boundary 3: Low / Medium (7 -> 8)
            {7, "Low"},
            {8, "Medium"},
            {9, "Medium"},

            // Boundary 4: Medium / High (14 -> 15)
            {14, "Medium"},
            {15, "High"},
            {16, "High"},

            // Boundary 5: High / Severe (30 -> 31)
            {30, "High"},
            {31, "Severe"},
            {32, "Severe"}
        });
    }

    @Test
    public void testFineTierBoundary() {
        if (expectedTier == null) {
            assertThrows(IllegalArgumentException.class, () -> Library.fineTier(days));
        } else {
            assertEquals(expectedTier, Library.fineTier(days));
        }
    }
}
