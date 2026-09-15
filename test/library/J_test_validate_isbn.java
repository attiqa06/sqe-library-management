package library;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;

/**
 * EP tests for validateIsbn(isbn): must be exactly 13 numeric digits,
 * no letters or symbols. Covers the 5 classes from docs/ep-analysis.md:
 *   1. Valid 13-digit ISBN         -> true
 *   2. Empty string                -> false
 *   3. Too-short string            -> false
 *   4. Contains letters/symbols    -> false
 *   5. Too-long string             -> false
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
            {"9780132350884",   true},   // Class 1: valid 13-digit ISBN
            {"",                false},  // Class 2: empty string
            {"97801323",        false},  // Class 3: too-short string (8 digits)
            {"97801323508X",    false},  // Class 4: contains letters/symbols
            {"97801323508842",  false},  // Class 5: too-long string (14 digits)
        });
    }

    @Test
    public void testValidateIsbn() {
        assertEquals(expectedValid, Library.validateIsbn(isbn));
    }
}
