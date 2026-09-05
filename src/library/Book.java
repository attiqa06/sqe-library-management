package library;

public class Book {
    private String title;
    private String isbn;
    private String author;
    private int totalCopies;
    private int availableCopies;

    public Book(String title, String isbn, String author, int totalCopies) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
    }

    public String getTitle() { return title; }
    public String getIsbn() { return isbn; }
    public String getAuthor() { return author; }
    public int getTotalCopies() { return totalCopies; }
    public int getAvailableCopies() { return availableCopies; }
    
    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }
}