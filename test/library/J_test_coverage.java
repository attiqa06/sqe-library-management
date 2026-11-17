package library;

import org.junit.Test;
import static org.junit.Assert.*;

public class J_test_coverage {

    // --- Closing gaps for Student.java ---
    @Test
    public void testStudentMethods() {
        Student student = new Student("Alice", "101");
        
        assertEquals("Alice", student.getName());
        assertEquals("101", student.getIdNumber());
        
        student.setName("Bob");
        student.setIdNumber("102");
        assertEquals("Bob", student.getName());
        assertEquals("102", student.getIdNumber());

        student.addScore(90.0);
        student.addScore(80.0);
        assertEquals(85.0, student.calculateAverage(), 0.01);

        assertNotNull(student.getScores());
        assertEquals(2, student.getScores().size());
    }
    
    // Separate test for the negative score to cover the exception branch
    @Test(expected = IllegalArgumentException.class)
    public void testAddNegativeScore() {
        Student student = new Student("Charlie", "103");
        student.addScore(-10.0);
    }

    // --- Closing gaps for LibraryMember.java ---
    @Test
    public void testLibraryMemberGetters() {
        LibraryMember member = new LibraryMember(1, "Alice", "alice@example.com");
        assertEquals(1, member.getId());
        assertEquals("alice@example.com", member.getEmail());
    }

            // --- Task 4: Strengthened Test (Fixing the Coverage Trap) ---
    @Test
    public void testAddBookStrengthened() {
        Library lib = new Library();
        int initialSize = lib.getCatalog().size();
        
        Book newBook = new Book("123-456", "Test Book", "Test Author", 1);
        lib.addBook(newBook);
        
        // Strong assertion: Check that the book was actually added
        assertEquals(initialSize + 1, lib.getCatalog().size());
        assertEquals("Test Book", lib.getCatalog().get(initialSize).getTitle());
    }

}
