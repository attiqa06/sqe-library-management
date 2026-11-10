package library;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * Lab 7 Task 4: one consolidated parametrized test replacing 7 separate
 * single-purpose borrowBook() tests. name = "{0}" is the Java equivalent of
 * pytest's ids=, so every case shows up by name in the Maven report.
 */
@RunWith(Parameterized.class)
public class J_test_borrow_book_sweep {

    private static final String TARGET_ISBN = "9780132350884";
    private static final int MEMBER_ID = 1;

    private final String caseName;
    private final int targetCopies;
    private final String isbnToBorrow;
    private final int alreadyBorrowed;
    private final boolean expectLimitError;
    private final int expectedCopiesAfter;

    public J_test_borrow_book_sweep(String caseName, int targetCopies, String isbnToBorrow,
                                    int alreadyBorrowed, boolean expectLimitError,
                                    int expectedCopiesAfter) {
        this.caseName = caseName;
        this.targetCopies = targetCopies;
        this.isbnToBorrow = isbnToBorrow;
        this.alreadyBorrowed = alreadyBorrowed;
        this.expectLimitError = expectLimitError;
        this.expectedCopiesAfter = expectedCopiesAfter;
    }

    @Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            // name,                              copies, isbn,            preloaded, limitError, copiesAfter
            {"single-copy-book-is-borrowed",       1, TARGET_ISBN,        0, false, 0},
            {"multi-copy-book-loses-exactly-one",  3, TARGET_ISBN,        0, false, 2},
            {"unknown-isbn-leaves-stock-untouched",2, "1111111111111",    0, false, 2},
            {"empty-isbn-leaves-stock-untouched",  2, "",                 0, false, 2},
            {"zero-copies-refuses-borrow",         0, TARGET_ISBN,        0, false, 0},
            {"member-one-below-limit-succeeds",    2, TARGET_ISBN,        4, false, 1},
            {"member-at-limit-throws",             2, TARGET_ISBN,        5, true,  2},
        });
    }

    private Library library;
    private Book target;

    // Function-scope fixture: library with one target book and one member.
    @Before
    public void setUp() {
        library = new Library();
        library.registerMember(new LibraryMember(MEMBER_ID, "Test Member", "t@example.com"));
        target = new Book("Clean Code", TARGET_ISBN, "R. Martin", targetCopies);
        library.addBook(target);
        // Pre-load the member with other books so the limit rule can be reached
        for (int i = 0; i < alreadyBorrowed; i++) {
            String isbn = "2000000000" + String.format("%03d", i);
            library.addBook(new Book("Filler " + i, isbn, "Author", 1));
            library.borrowBook(MEMBER_ID, isbn);
        }
    }

    @Test
    public void borrowBookBehavesAsExpected() {
        if (expectLimitError) {
            assertThrows(caseName, IllegalStateException.class,
                    () -> library.borrowBook(MEMBER_ID, isbnToBorrow));
        } else {
            library.borrowBook(MEMBER_ID, isbnToBorrow);
        }
        assertEquals(caseName, expectedCopiesAfter, target.getAvailableCopies());
    }
}
