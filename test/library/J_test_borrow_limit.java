package library;

import org.junit.Before;
import org.junit.BeforeClass;
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
 * Refactored in Lab 7 Task 1 to use two fixtures instead of repeating
 * setup inline in every parametrized run:
 *
 *   - buildIsbnPool()  [@BeforeClass, "module" scope]: runs exactly ONCE
 *     for the whole class, regardless of how many parametrized cases run.
 *     Appropriate here because the ISBN pool is pure, read-only string
 *     formatting that no test ever mutates -- recomputing it per test
 *     would just waste time for identical output. Module/class scope is
 *     the right call whenever setup is expensive-ish AND immutable AND
 *     safe to share across every test in the class.
 *
 *   - setUpLibrary()  [@Before, default "function" scope]: runs fresh
 *     before EVERY @Test execution. Appropriate here because each test
 *     mutates the Library (borrowing changes availableCopies and the
 *     borrowedBooks list) -- sharing one Library instance across tests
 *     would let one test's borrows leak into the next test's starting
 *     state, breaking the independence/repeatability a healthy test
 *     suite needs. Any fixture with state a test writes to should
 *     default to function scope unless you have a specific, deliberate
 *     reason to share it.
 *
 * Boundary/EP cases covered (unchanged from Lab 6 Task 3):
 *   {3, true}  - EP: within limit, borrowing the 4th book succeeds
 *   {5, false} - EP: at limit, borrowing the 6th book throws
 *   {4, true}  - BVA: limit - 1, borrowing the 5th book succeeds
 *   {6, false} - BVA: limit + 1 (forced via reflection, unreachable
 *                through the public API), borrowing the 7th book throws
 */
@RunWith(Parameterized.class)
public class J_test_borrow_limit {

    // ----- module-scope fixture: built once for the whole class -----
    private static String[] setupIsbnPool;

    @BeforeClass
    public static void buildIsbnPool() {
        setupIsbnPool = new String[10];
        for (int i = 0; i < setupIsbnPool.length; i++) {
            setupIsbnPool[i] = "000000000" + String.format("%04d", i);
        }
    }

    // ----- function-scope fixture: rebuilt before every test -----
    private Library library;
    private final int memberId = 1;

    @Before
    public void setUpLibrary() {
        library = new Library();
        library.registerMember(new LibraryMember(memberId, "Test Member", "test@example.com"));
    }

    private final int initialBorrowedCount;
    private final boolean expectSuccess;

    public J_test_borrow_limit(int initialBorrowedCount, boolean expectSuccess) {
        this.initialBorrowedCount = initialBorrowedCount;
        this.expectSuccess = expectSuccess;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            {3, true},
            {5, false},
            {4, true},
            {6, false}
        });
    }

    @Test
    public void testBorrowLimit() throws Exception {
        if (initialBorrowedCount <= 5) {
            // Reachable state: pre-load through the real public API,
            // using the shared (module-scope) ISBN pool.
            for (int i = 0; i < initialBorrowedCount; i++) {
                String isbn = setupIsbnPool[i];
                Book book = new Book("Setup Book " + i, isbn, "Author", 1);
                library.addBook(book);
                library.borrowBook(memberId, isbn);
            }
        } else {
            // Unreachable through the public API (borrowBook() itself
            // blocks ever reaching 6). Force it via reflection to
            // defensively verify borrowBook() still refuses a further
            // borrow from this state.
            Field field = Library.class.getDeclaredField("borrowedBooks");
            field.setAccessible(true);
            @SuppressWarnings("unchecked")
            List<Integer> borrowedBooks = (List<Integer>) field.get(library);
            for (int i = 0; i < initialBorrowedCount; i++) {
                borrowedBooks.add(memberId);
            }
        }

        String newIsbn = "9999999999999";
        Book newBook = new Book("New Book", newIsbn, "Author", 1);
        library.addBook(newBook);

        if (expectSuccess) {
            library.borrowBook(memberId, newIsbn);
            assertEquals(0, newBook.getAvailableCopies());
        } else {
            assertThrows(IllegalStateException.class, () -> {
                library.borrowBook(memberId, newIsbn);
            });
        }
    }
}
