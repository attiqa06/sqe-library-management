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
 * EP tests for the borrow limit rule: a member may have 0-5 books on
 * loan at once.
 *   Class 1 (valid):   member already has 3 books, borrows a 4th -> succeeds
 *   Class 2 (invalid): member already has 5 books, attempts a 6th -> throws
 */
@RunWith(Parameterized.class)
public class J_test_borrow_limit {

    private final int initialBorrowedCount;
    private final boolean expectSuccess;

    public J_test_borrow_limit(int initialBorrowedCount, boolean expectSuccess) {
        this.initialBorrowedCount = initialBorrowedCount;
        this.expectSuccess = expectSuccess;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            {3, true},   // within limit: borrowing the 4th book should succeed
            {5, false}   // at limit: borrowing the 6th book should throw
        });
    }

    @Test
    public void testBorrowLimit() {
        Library library = new Library();
        int memberId = 1;
        library.registerMember(new LibraryMember(memberId, "Test Member", "test@example.com"));

        // Pre-load the member with `initialBorrowedCount` books already on loan
        for (int i = 0; i < initialBorrowedCount; i++) {
            String isbn = "000000000" + String.format("%04d", i);
            Book book = new Book("Setup Book " + i, isbn, "Author", 1);
            library.addBook(book);
            library.borrowBook(memberId, isbn);
        }

        // Now attempt to borrow one more book
        String newIsbn = "9999999999999";
        Book newBook = new Book("New Book", newIsbn, "Author", 1);
        library.addBook(newBook);

        if (expectSuccess) {
            // Should NOT throw
            library.borrowBook(memberId, newIsbn);
            assertEquals(0, newBook.getAvailableCopies());
        } else {
            // Should throw when the limit would be exceeded
            assertThrows(IllegalStateException.class, () -> {
                library.borrowBook(memberId, newIsbn);
            });
        }
    }
}
