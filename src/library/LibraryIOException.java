package library;

/** Thrown when the library cannot read or write its data on disk. */
public class LibraryIOException extends RuntimeException {
    public LibraryIOException(String message, Throwable cause) {
        super(message, cause);
    }
}