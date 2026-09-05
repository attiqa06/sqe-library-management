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
        books.add(book);
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
    // FIX: Check if copies are available
    if (book.getAvailableCopies() <= 0) {
        System.out.println("No copies available to borrow!");
        return false;
    }
    book.setAvailableCopies(book.getAvailableCopies() - 1);
    return true;
}
}