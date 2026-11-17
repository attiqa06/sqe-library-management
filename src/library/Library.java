package library;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private static final int LOW_MAX_DAYS = 7;
    private static final int MEDIUM_MAX_DAYS = 14;
    private static final int HIGH_MAX_DAYS = 30;
    private static final int MAX_BORROWED_BOOKS = 5;
    private List<Book> catalog = new ArrayList<>();
    private List<LibraryMember> members = new ArrayList<>();
    private List<Integer> borrowedBooks = new ArrayList<>();

    public static String fineTier(int daysOverdue) {
        if (daysOverdue < 0) {
            throw new IllegalArgumentException("daysOverdue cannot be negative");
        }
        if (daysOverdue == 0) {
            return "None";
        } else if (daysOverdue <= LOW_MAX_DAYS) {
            return "Low";
        } else if (daysOverdue <= MEDIUM_MAX_DAYS) {
            return "Medium";
        } else if (daysOverdue <= HIGH_MAX_DAYS) {
            return "High";
        } else {
            return "Severe";
        }
    }

    // Validate ISBN: must be exactly 13 numeric digits, no letters or symbols
    public static boolean validateIsbn(String isbn) {
        if (isbn == null || isbn.length() != 13) {
            return false;
        }
        for (int i = 0; i < isbn.length(); i++) {
            if (!Character.isDigit(isbn.charAt(i))) {
                return false;
            }
        }
        return true;
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

        // Check member borrowing limit
        int borrowedCount = 0;
        for (int id : borrowedBooks) {
            if (id == memberId) borrowedCount++;
        }

        if (borrowedCount >= MAX_BORROWED_BOOKS) {
            throw new IllegalStateException(
                "Borrowing limit exceeded: member " + memberId + " already has " + MAX_BORROWED_BOOKS + " books on loan");
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

    // Lab 7 Task 2: sum of available copies across every book in the catalog
    public int totalAvailableCopies() {
        int total = 0;
        for (Book b : catalog) {
            total += b.getAvailableCopies();
        }
        return total;
    }

    // Lab 7 Task 3: write the catalog to disk, one line per book:
    // title,isbn,author,availableCopies
    public void exportCatalog(String path) {
        StringBuilder content = new StringBuilder();
        for (Book b : catalog) {
            content.append(b.getTitle()).append(',')
                   .append(b.getIsbn()).append(',')
                   .append(b.getAuthor()).append(',')
                   .append(b.getAvailableCopies()).append('\n');
        }
        try (Writer writer = openWriter(path)) {
            writer.write(content.toString());
        } catch (IOException e) {
            throw new LibraryIOException("Could not export catalog to " + path, e);
        }
    }

    // The single place a file is opened. Package-private so tests can
    // replace it (the Java equivalent of mocking Python's open()).
    Writer openWriter(String path) throws IOException {
        return new FileWriter(path);
    }

    // Get catalog (for testing)
    public List<Book> getCatalog() {
        return catalog;
    }

    public boolean isSameBook(String a, String b) {
        return a != null && a.equals(b);
    }

    public int safeParseCopies(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public int firstTitleLength(String isbn) {
        Book found = null;
        for (Book b : catalog) {
            if (b.getIsbn().equals(isbn)) {
                found = b;
            }
        }
        if (found == null) {
            return 0;
        }
        return found.getTitle().length();
    }

    public boolean sameValue(int x, int y) {
        return x == y;
    }

    public String trimTitle(String title) {
        return title.trim();
    }

    public void removeMember(int id) {
        borrowedBooks.remove(Integer.valueOf(id));
    }
}
