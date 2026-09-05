package library;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<Integer> members;

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    public void addBook(Book book) {
        for (Book existingBook : books) {
            if (existingBook.getIsbn().equals(book.getIsbn())) {
                System.out.println("A book with this ISBN already exists!");
                return;
            }
        }
        books.add(book);
    }

    public boolean canBorrow(int memberId) {
        int borrowedCount = 0;
        for (Book book : books) {
            // Count borrowed books for this member
        }
        return borrowedCount < 3;
    }

    public Book findBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    public boolean borrowBook(int memberId, String isbn) {
        Book book = findBook(isbn);
        if (book == null) {
            return false;
        }
        if (book.getAvailableCopies() <= 0) {
            System.out.println("No copies available to borrow!");
            return false;
        }
        if (!canBorrow(memberId)) {
            System.out.println("Member has reached borrowing limit (3 books max)!");
            return false;
        }
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        return true;
    }
}