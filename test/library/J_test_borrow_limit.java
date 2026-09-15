package library;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * EP + BVA tests for the borrow limit rule: a member may have 0-5 books
 * on loan at once (Library.borrowBook).
 *
 * EP representatives (Lab 5):
 *   {3, true}  - within limit, borrowing a 4th -> succeeds
 *   {5, false} - at limit, borrowing a 6th -> throws
 *
 * BVA boundary points added (Lab 6, Task 3) around the 5/6 cut-off:
 *   {4, true}  - one below the limit, borrowing the 5th -> succeeds
 *   {5, false} - the limit itself, borrowing the 6th -> throws (same as EP class 2)
 *   {6, false} - one past the limit, attempting a 7th -> throws
 *
 * The {6, ...} case represents a member state the public API can never
 * actually produce (borrowBook() itself refuses to let anyone reach 6
 * books), so it is set up via reflection directly against the private
 * borrowedBooks list. This is a defensive/robustness check: even if that
 * state were ever reached some other way, borrowBook() must still refuse
 * to add a 7th book rather than silently accepting it.
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
            {3, true},   // EP class 1: within limit, borrowing the 4th book should succeed
            {5, false},  // EP class 2: at limit, borrowing the 6th book should throw

            {4, true},   // BVA: limit - 1, borrowing the 5th book should succeed
            {6, false}   // BVA: limit + 1 (forced), borrowing the 7th book should throw
        });
    }

    @Test
    public void testBorrowLimit() throws Exception {
        Library library = new Library();
        int memberId = 1;
        library.registerMember(new LibraryMember(memberId, "Test Member", "test@example.com"));

        if (initialBorrowedCount <= 5) {
            // Reachable state: pre-load through the real public API, same as Lab 5.
            for (int i = 0; i < initialBorrowedCount; i++) {
                String isbn = "000000000" + String.format("%04d", i);
                Book book = new Book("Setup Book " + i, isbn, "Author", 1);
                library.addBook(book);
                library.borrowBook(memberId, isbn);
            }
        } else {
            // Unreachable through the public API (borrowBook() itself blocks
            // ever reaching 6). Force it via reflection to defensively verify
            // borrowBook() still refuses a further borrow from this state.
            Field field = Library.class.getDeclaredField("borrowedBooks");
            field.setAccessible(true);
            @SuppressWarnings("unchecked")
            List<Integer> borrowedBooks = (List<Integer>) field.get(library);
            for (int i = 0; i < initialBorrowedCount; i++) {
                borrowedBooks.add(memberId);
            }
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