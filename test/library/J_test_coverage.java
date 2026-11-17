package library;

import org.junit.Test;
import static org.junit.Assert.*;

public class J_test_coverage {

    // --- Closing gaps for Student.java ---
    @Test
    public void testStudentMethods() {
        Student student = new Student("Alice", 101);
        
        // Cover getters
        assertEquals("Alice", student.getName());
        assertEquals(101, student.getNumber());
        
        // Cover setters
        student.setName("Bob");
        student.setNumber(102);
        assertEquals("Bob", student.getName());
        assertEquals(102, student.getNumber());

        // Cover addScore and calculateAverage
        student.addScore(90.0);
        student.addScore(80.0);
        assertEquals(85.0, student.calculateAverage(), 0.01);

        // Cover getScores
        assertNotNull(student.getScores());
        assertEquals(2, student.getScores().size());
    }

    // --- Closing gaps for LibraryMember.java ---
    @Test
    public void testLibraryMemberGetters() {
        LibraryMember member = new LibraryMember(1, "Alice", "alice@example.com");
        
        // Cover getId() and getEmail() which were red
        assertEquals(1, member.getId());
        assertEquals("alice@example.com", member.getEmail());
    }
}
