package library;

import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.io.Writer;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Lab 7 Task 3: mocking file I/O. openWriter() is the only place a file is
 * opened, so replacing it with a mock Writer means no real file is touched.
 */
public class J_test_export_catalog {

    private Library library;   // a Mockito spy: real behaviour, openWriter() replaced
    private Writer mockWriter;

    @Before
    public void setUp() {
        library = spy(new Library());
        mockWriter = mock(Writer.class);
        library.addBook(new Book("Clean Code", "9780132350884", "R. Martin", 2));
        library.addBook(new Book("The Pragmatic Programmer", "9780135957059", "D. Thomas", 1));
    }

    @Test
    public void exportCatalogWritesExpectedContent() throws IOException {
        // Arrange: openWriter() hands back the mock instead of a real file
        doReturn(mockWriter).when(library).openWriter(anyString());

        // Act
        library.exportCatalog("catalog.csv");

        // Assert
        String expected = "Clean Code,9780132350884,R. Martin,2\n"
                        + "The Pragmatic Programmer,9780135957059,D. Thomas,1\n";
        verify(library).openWriter("catalog.csv");
        verify(mockWriter).write(expected);
        verify(mockWriter).close();   // try-with-resources released the file
    }

    @Test
    public void exportCatalogWrapsIoFailureInLibraryIOException() throws IOException {
        // Arrange: opening the file fails
        IOException rawFailure = new IOException("disk full");
        doThrow(rawFailure).when(library).openWriter(anyString());

        // Act + Assert: caller sees our exception, not the raw IOException
        LibraryIOException ex = assertThrows(LibraryIOException.class,
                () -> library.exportCatalog("catalog.csv"));
        assertEquals("Could not export catalog to catalog.csv", ex.getMessage());
        assertSame(rawFailure, ex.getCause());   // original error kept for debugging
    }

    @Test
    public void writeFailureAlsoBecomesLibraryIOException() throws IOException {
        // Arrange: file opens fine but writing fails
        doReturn(mockWriter).when(library).openWriter(anyString());
        doThrow(new IOException("write error")).when(mockWriter).write(anyString());

        // Act + Assert
        assertThrows(LibraryIOException.class, () -> library.exportCatalog("catalog.csv"));
    }
}
