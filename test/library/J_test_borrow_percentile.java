package library;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class J_test_borrow_percentile {

    private static final String ISBN = "1234567890123";

    @Test
    public void emptyLibrary_returnsZero() {
        assertEquals(0.0, new Library().borrowPercentile(1), 0.001);
    }

    @Test
    public void singleMember_returnsZero() {
        Library lib = new Library();
        lib.registerMember(new LibraryMember(1, "Alice", "alice@example.com"));
        assertEquals(0.0, lib.borrowPercentile(1), 0.001);
    }

    @Test
    public void threeMembers_ranksByLoanCount() {
        Library lib = new Library();
        lib.addBook(new Book("Clean Code", ISBN, "Robert Martin", 10));
        lib.registerMember(new LibraryMember(1, "Alice", "alice@example.com"));
        lib.registerMember(new LibraryMember(2, "Bob", "bob@example.com"));
        lib.registerMember(new LibraryMember(3, "Cara", "cara@example.com"));
        lib.borrowBook(2, ISBN);
        lib.borrowBook(3, ISBN);
        lib.borrowBook(3, ISBN);
        assertEquals(0.0, lib.borrowPercentile(1), 0.001);
        assertEquals(33.333, lib.borrowPercentile(2), 0.01);
        assertEquals(66.667, lib.borrowPercentile(3), 0.01);
    }
}
