package library;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class J_test_fine_tier {

    private int days;
    private String expectedTier;

    public J_test_fine_tier(int days, String expectedTier) {
        this.days = days;
        this.expectedTier = expectedTier;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            {0, "None"},
            {4, "Low"},
            {10, "Medium"},
            {20, "High"},
            {45, "Severe"}
        });
    }

    @Test
    public void testFineTier() {
        String actual = Library.fineTier(days);
        assertEquals(expectedTier, actual);
    }
}
