package library;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Book book = new Book("Clean Code", "9780132350884", "R. Martin", 1);
        library.addBook(book);
        
        // This should work
        library.borrowBook(1, "9780132350884");
        
        // This should FAIL but it doesn't! (BUG!)
        library.borrowBook(2, "9780132350884");
        
        System.out.println("Available copies: " + book.getAvailableCopies());
        // Prints -1 !!!
    }
}
