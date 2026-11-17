package library;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/** Lab 10: tests for the methods fixed during the SonarCloud triage. */
public class J_test_extra_methods {

    private static final String ISBN = "1234567890123";
    private Library library;

    @Before
    public void setUp() {
        library = new Library();
        library.addBook(new Book("Clean Code", ISBN, "Robert Martin", 3));
    }

    @Test
    public void isSameBook_equalStrings() {
        assertTrue(library.isSameBook(new String("abc"), new String("abc")));
    }

    @Test
    public void isSameBook_differentStrings() {
        assertFalse(library.isSameBook("abc", "xyz"));
    }

    @Test
    public void isSameBook_nullFirstArgument() {
        assertFalse(library.isSameBook(null, "abc"));
    }

    @Test
    public void safeParseCopies_validNumber() {
        assertEquals(7, library.safeParseCopies("7"));
    }

    @Test
    public void safeParseCopies_invalidNumber() {
        assertEquals(0, library.safeParseCopies("abc"));
    }

    @Test
    public void firstTitleLength_bookFound() {
        assertEquals(10, library.firstTitleLength(ISBN));
    }

    @Test
    public void firstTitleLength_bookMissing() {
        assertEquals(0, library.firstTitleLength("0000000000000"));
    }

    @Test
    public void sameValue_equalAndDifferent() {
        assertTrue(library.sameValue(3, 3));
        assertFalse(library.sameValue(3, 4));
    }

    @Test
    public void trimTitle_removesSpaces() {
        assertEquals("hello", library.trimTitle("  hello  "));
    }

    @Test
    public void removeMember_clearsBorrowRecord() {
        Library big = new Library();
        big.addBook(new Book("Big Book", ISBN, "Author", 20));
        for (int i = 0; i < 5; i++) {
            big.borrowBook(1, ISBN);
        }
        big.removeMember(1);
        // removeMember drops one record (5 -> 4), so a 6th borrow is allowed.
        // Without the removal this call would throw IllegalStateException.
        big.borrowBook(1, ISBN);
        assertEquals(14, big.getCatalog().get(0).getAvailableCopies());
    }

    @Test
    public void returnBook_returnsZero() {
        assertEquals(0.0, library.returnBook(1, ISBN), 0.0001);
    }
}
