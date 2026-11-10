package library;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/** Lab 7 Task 2: Library.totalAvailableCopies(), fixture + Arrange-Act-Assert. */
public class J_test_total_available_copies {

    private Library library;

    // Function-scope fixture: a fresh, empty Library before every test.
    @Before
    public void setUpLibrary() {
        library = new Library();
    }

    @Test
    public void emptyCatalogHasZeroCopies() {
        // Act
        int result = library.totalAvailableCopies();
        // Assert
        assertEquals(0, result);
    }

    @Test
    public void singleBookReturnsItsCopies() {
        // Arrange
        library.addBook(new Book("Clean Code", "9780132350884", "R. Martin", 2));
        // Act + Assert
        assertEquals(2, library.totalAvailableCopies());
    }

    @Test
    public void multipleBooksAreSummed() {
        // Arrange (same data as the lab's worked example: 2 + 1 = 3)
        library.addBook(new Book("Clean Code", "9780132350884", "R. Martin", 2));
        library.addBook(new Book("The Pragmatic Programmer", "9780135957059", "D. Thomas", 1));
        // Act + Assert
        assertEquals(3, library.totalAvailableCopies());
    }

    @Test
    public void borrowingReducesTheTotal() {
        // Arrange
        library.addBook(new Book("Clean Code", "9780132350884", "R. Martin", 2));
        library.registerMember(new LibraryMember(1, "Ali", "ali@example.com"));
        // Act
        library.borrowBook(1, "9780132350884");
        // Assert
        assertEquals(1, library.totalAvailableCopies());
    }
}
