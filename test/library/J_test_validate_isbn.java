package library;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;

/**
 * EP + BVA tests for validateIsbn(isbn): must be exactly 13 numeric
 * digits, no letters or symbols.
 *
 * EP classes (Lab 5, docs/ep-analysis.md):
 *   1. Valid 13-digit ISBN         -> true
 *   2. Empty string                -> false
 *   3. Too-short string            -> false
 *   4. Contains letters/symbols    -> false
 *   5. Too-long string             -> false
 *
 * BVA length boundary points added (Lab 6, Task 4), all-numeric strings
 * at every length around the 13-digit cut-off:
 *   11 digits -> false (too short)
 *   12 digits -> false (too short, one below the boundary)
 *   13 digits -> true  (the boundary itself, same as EP class 1)
 *   14 digits -> false (one above the boundary, same value as EP class 5)
 *   15 digits -> false (too long)
 */
@RunWith(Parameterized.class)
public class J_test_validate_isbn {

    private final String isbn;
    private final boolean expectedValid;

    public J_test_validate_isbn(String isbn, boolean expectedValid) {
        this.isbn = isbn;
        this.expectedValid = expectedValid;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            // EP classes (Lab 5)
            {"9780132350884",    true},   // Class 1: valid 13-digit ISBN
            {"",                 false},  // Class 2: empty string
            {"97801323",         false},  // Class 3: too-short string (8 digits)
            {"97801323508X",     false},  // Class 4: contains letters/symbols
            {"97801323508842",   false},  // Class 5: too-long string (14 digits)

            // BVA length boundaries (Lab 6, Task 4) - all-numeric strings
            // at every length around the 13-digit cut-off
            {"97801323508",      false},  // 11 digits - too short
            {"978013235088",     false},  // 12 digits - too short (boundary - 1)
            {"9780132350884",    true},   // 13 digits - the boundary itself
            {"97801323508842",   false},  // 14 digits - too long (boundary + 1)
            {"978013235088420",  false},  // 15 digits - too long
        });
    }

    @Test
    public void testValidateIsbn() {
        assertEquals(expectedValid, Library.validateIsbn(isbn));
    }
}
