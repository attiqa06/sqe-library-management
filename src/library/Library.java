package library;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> catalog = new ArrayList<>();
    private List<LibraryMember> members = new ArrayList<>();
    private List<Integer> borrowedBooks = new ArrayList<>();

    public static String fineTier(int daysOverdue) {
        if (daysOverdue < 0) {
            throw new IllegalArgumentException("daysOverdue cannot be negative");
        }
        if (daysOverdue == 0) {
            return "None";
        } else if (daysOverdue <= 7) {
            return "Low";
        } else if (daysOverdue <= 14) {
            return "Medium";
        } else if (daysOverdue <= 30) {
            return "High";
        } else {
            return "Severe";
        }
    }

    // Add a book to the catalog
    public void addBook(Book book) {
        // Check for duplicate ISBN
        for (Book b : catalog) {
            if (b.getIsbn().equals(book.getIsbn())) {
                System.out.println("A book with this ISBN already exists!");
                return;
            }
        }
        catalog.add(book);
    }

    // Register a new member
    public void registerMember(LibraryMember member) {
        members.add(member);
        System.out.println("Member registered: " + member.getName());
    }

    // Borrow a book
    public void borrowBook(int memberId, String isbn) {
        // Find the book
        Book bookToBorrow = null;
        for (Book b : catalog) {
            if (b.getIsbn().equals(isbn)) {
                bookToBorrow = b;
                break;
            }
        }

        if (bookToBorrow == null) {
            System.out.println("Book not found!");
            return;
        }

        if (bookToBorrow.getAvailableCopies() <= 0) {
            System.out.println("No copies available!");
            return;
        }

        // Check member borrowing limit (assuming limit is 5)
        int borrowedCount = 0;
        for (int id : borrowedBooks) {
            if (id == memberId) borrowedCount++;
        }

        if (borrowedCount >= 5) {
            throw new IllegalStateException(
                "Borrowing limit exceeded: member " + memberId + " already has 5 books on loan");
        }

        // Borrow the book
        bookToBorrow.setAvailableCopies(bookToBorrow.getAvailableCopies() - 1);
        borrowedBooks.add(memberId);
        System.out.println("Book borrowed successfully!");
    }

    // Return a book
    public double returnBook(int memberId, String isbn) {
        System.out.println("Returning book...");
        // Simple implementation - just return 0.0 fine for now
        return 0.0;
    }

    // Get catalog (for testing)
    public List<Book> getCatalog() {
        return catalog;
    }
}
