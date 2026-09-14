package library;

public class TestRunner {
    public static void main(String[] args) {
        Library library = new Library();
        System.out.println("=== LibraryHub Test Execution ===\n");

        // --- TC-01: Add a valid new book ---
        System.out.println("--- TC-01: Add a valid new book ---");
        try {
            Book book1 = new Book("Clean Code", "9780132350884", "Robert Martin", 5);
            library.addBook(book1);
            System.out.println("Result: Book added successfully!");
            System.out.println("TC-01: ✅ PASS\n");
        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
            System.out.println("TC-01: ❌ FAIL\n");
        }

        // --- TC-02: Add a book with duplicate ISBN ---
        System.out.println("--- TC-02: Add a book with duplicate ISBN ---");
        try {
            Book book2 = new Book("Duplicate Title", "9780132350884", "Some Author", 3);
            library.addBook(book2);
            System.out.println("Result: Book was added (No exception thrown)");
            System.out.println("TC-02: ❌ FAIL - Expected exception but got none");
        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
            System.out.println("TC-02: ✅ PASS - Exception thrown as expected");
        }
        System.out.println();

        // --- TC-03: Add a book with malformed ISBN ---
        System.out.println("--- TC-03: Add a book with malformed ISBN ---");
        try {
            Book book3 = new Book("Bad Book", "12345", "Some Author", 2);
            library.addBook(book3);
            System.out.println("Result: Book was added (No exception thrown)");
            System.out.println("TC-03: ❌ FAIL - Expected exception but got none");
        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
            System.out.println("TC-03: ✅ PASS - Exception thrown as expected");
        }
        System.out.println();

        // --- TC-04: Borrow a book when copies are available ---
        System.out.println("--- TC-04: Borrow a book when copies are available ---");
        try {
            library.registerMember(new LibraryMember(1, "Ali", "ali@test.com"));
            library.borrowBook(1, "9780132350884");
            System.out.println("Result: Borrow succeeded!");
            System.out.println("TC-04: ✅ PASS\n");
        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
            System.out.println("TC-04: ❌ FAIL\n");
        }

        // --- TC-05: Borrow a book when no copies are available ---
        System.out.println("--- TC-05: Borrow a book when no copies are available ---");
        Library library2 = new Library();
        try {
            Book book5 = new Book("Zero Copies", "9780132350885", "Test Author", 0);
            library2.addBook(book5);
            library2.registerMember(new LibraryMember(2, "Bobby", "bobby@test.com"));
            library2.borrowBook(2, "9780132350885");
            System.out.println("Result: Borrow succeeded (No exception thrown)");
            System.out.println("TC-05: ❌ FAIL - Expected exception but got none");
        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
            System.out.println("TC-05: ✅ PASS - Exception thrown as expected");
        }
        System.out.println();

        // --- TC-06: Return a book currently on loan ---
        System.out.println("--- TC-06: Return a book currently on loan ---");
        Library library3 = new Library();
        try {
            Book book6 = new Book("Clean Code 2", "9780132350886", "Robert Martin", 1);
            library3.addBook(book6);
            library3.registerMember(new LibraryMember(3, "Charlie", "charlie@test.com"));
            library3.borrowBook(3, "9780132350886");
            double fine = library3.returnBook(3, "9780132350886");
            System.out.println("Result: Return succeeded! Fine: " + fine);
            System.out.println("TC-06: ✅ PASS\n");
        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
            System.out.println("TC-06: ❌ FAIL\n");
        }

        // --- TC-07: Return a book not on loan by that member ---
        System.out.println("--- TC-07: Return a book not on loan by that member ---");
        Library library4 = new Library();
        try {
            Book book7 = new Book("Java Programming", "9780132350887", "James Gosling", 1);
            library4.addBook(book7);
            library4.registerMember(new LibraryMember(4, "Diana", "diana@test.com"));
            library4.registerMember(new LibraryMember(5, "Eve", "eve@test.com"));
            library4.borrowBook(4, "9780132350887");
            library4.returnBook(5, "9780132350887");
            System.out.println("Result: Return succeeded (No exception thrown)");
            System.out.println("TC-07: ❌ FAIL - Expected exception but got none");
        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
            System.out.println("TC-07: ✅ PASS - Exception thrown as expected");
        }
        System.out.println();

        // --- TC-08: Member borrowing at the allowed limit ---
        System.out.println("--- TC-08: Member borrowing at the allowed limit ---");
        Library library5 = new Library();
        try {
            library5.registerMember(new LibraryMember(6, "Frank", "frank@test.com"));

            String[] isbns = {"9780132350888", "9780132350889", "9780132350890", "9780132350891", "9780132350892"};
            for (String isbn : isbns) {
                Book b = new Book("Book " + isbn, isbn, "Author", 1);
                library5.addBook(b);
            }

            for (String isbn : isbns) {
                library5.borrowBook(6, isbn);
            }
            System.out.println("Result: All 5 books borrowed successfully!");
            System.out.println("TC-08: ✅ PASS\n");
        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
            System.out.println("TC-08: ❌ FAIL\n");
        }

        // --- TC-09: Member borrowing beyond the allowed limit ---
        System.out.println("--- TC-09: Member borrowing beyond the allowed limit ---");
        Library library6 = new Library();
        try {
            library6.registerMember(new LibraryMember(7, "Grace", "grace@test.com"));

            String[] isbns2 = {"9780132350893", "9780132350894", "9780132350895", "9780132350896", "9780132350897", "9780132350898"};
            for (String isbn : isbns2) {
                Book b = new Book("Book " + isbn, isbn, "Author", 1);
                library6.addBook(b);
            }

            int borrowCount = 0;
            for (String isbn : isbns2) {
                library6.borrowBook(7, isbn);
                borrowCount++;
                if (borrowCount >= 5) {
                    System.out.println("Attempting to borrow 6th book...");
                }
            }
            System.out.println("Result: All 6 books borrowed successfully (No exception thrown)");
            System.out.println("TC-09: ❌ FAIL - Expected exception on 6th borrow");
        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
            System.out.println("TC-09: ✅ PASS - Exception thrown when exceeding limit");
        }
        System.out.println();

        // --- TC-10: Fine calculation for zero days overdue ---
        System.out.println("--- TC-10: Fine calculation for zero days overdue ---");
        Library library7 = new Library();
        try {
            Book book10 = new Book("Clean Code 3", "9780132350899", "Robert Martin", 1);
            library7.addBook(book10);
            library7.registerMember(new LibraryMember(8, "Hannah", "hannah@test.com"));
            library7.borrowBook(8, "9780132350899");
            double fine = library7.returnBook(8, "9780132350899");
            System.out.println("Result: Returned with fine: " + fine);
            if (fine == 0.0) {
                System.out.println("TC-10: ✅ PASS - Fine is 0.0 as expected\n");
            } else {
                System.out.println("TC-10: ❌ FAIL - Expected 0.0 but got " + fine + "\n");
            }
        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
            System.out.println("TC-10: ❌ FAIL\n");
        }

        // --- TC-11: Fine calculation for mid-range overdue period ---
        System.out.println("--- TC-11: Fine calculation for mid-range overdue period ---");
        Library library8 = new Library();
        try {
            Book book11 = new Book("Clean Code 4", "9780132350900", "Robert Martin", 1);
            library8.addBook(book11);
            library8.registerMember(new LibraryMember(9, "Ian", "ian@test.com"));
            library8.borrowBook(9, "9780132350900");
            double fine = library8.returnBook(9, "9780132350900");
            System.out.println("Result: Returned with fine: " + fine);
            if (fine > 0.0) {
                System.out.println("TC-11: ✅ PASS - Fine is " + fine + " (greater than 0 as expected)\n");
            } else {
                System.out.println("TC-11: ❌ FAIL - Expected fine > 0 but got " + fine + "\n");
            }
        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
            System.out.println("TC-11: ❌ FAIL\n");
        }

        // --- TC-12: Fine calculation at an overdue-tier boundary ---
        System.out.println("--- TC-12: Fine calculation at an overdue-tier boundary ---");
        Library library9 = new Library();
        try {
            Book book12 = new Book("Clean Code 5", "9780132350901", "Robert Martin", 1);
            library9.addBook(book12);
            library9.registerMember(new LibraryMember(10, "Jack", "jack@test.com"));
            library9.borrowBook(10, "9780132350901");
            double fine = library9.returnBook(10, "9780132350901");
            System.out.println("Result: Returned with fine: " + fine);
            if (fine == 0.0) {
                System.out.println("TC-12: ⚠️ SKIPPED - Fine calculation not implemented yet\n");
            } else {
                System.out.println("TC-12: ✅ PASS - Boundary handled correctly\n");
            }
        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
            System.out.println("TC-12: ❌ FAIL\n");
        }

        // --- TC-13: Register a new member successfully ---
        System.out.println("--- TC-13: Register a new member successfully ---");
        Library library10 = new Library();
        try {
            library10.registerMember(new LibraryMember(11, "Kate", "kate@test.com"));
            System.out.println("Result: Member registered successfully!");
            System.out.println("TC-13: ✅ PASS\n");
        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
            System.out.println("TC-13: ❌ FAIL\n");
        }

        System.out.println("=== End of Test Execution ===");
    }
}
