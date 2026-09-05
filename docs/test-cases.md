# Test Cases for LibraryHub (LMS)

**Version:** 1.0  
**Date:** 2026-09-05  
**Author:** [Your Name]  
**Project:** LibraryHub - Library Management System

---

## Test Case Table

| ID | Title | Requirement | Preconditions | Steps | Expected Result | Priority | Type | Execution Result | Linked Issue |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| TC-01 | Add a valid new book | REQ-1 | Library is empty. | 1. `Book book = new Book("Clean Code", "9780132350884", "Robert Martin", 5);` <br> 2. `library.addBook(book);` <br> 3. Check catalog size. | Book is added successfully. Catalog size becomes 1. No exception thrown. | High | Functional / Positive | ✅ PASS | - |
| TC-02 | Add a book with duplicate ISBN | REQ-1 | Library already contains a book with ISBN `9780132350884`. | 1. `Book duplicate = new Book("Duplicate Title", "9780132350884", "Some Author", 3);` <br> 2. `library.addBook(duplicate);` | An `IllegalArgumentException` is thrown with message "ISBN already exists". Catalog size remains unchanged. | High | Negative / Functional | ❌ FAIL | #22 |
| TC-03 | Add a book with malformed ISBN | REQ-1 | Library is empty. | 1. `Book invalid = new Book("Bad Book", "12345", "Author", 2);` <br> 2. `library.addBook(invalid);` | An `IllegalArgumentException` is thrown with message "Invalid ISBN format". Catalog remains empty. | Medium | Negative / Functional | ❌ FAIL | #23 |
| TC-04 | Borrow a book when copies are available | REQ-2 | Library has `Book("Clean Code", "9780132350884", "Martin", 2)` and a registered `Member` with ID 1. | 1. `library.borrowBook(1, "9780132350884");` <br> 2. Check available copies. | Borrow succeeds. Available copies decrease from 2 to 1. No exception thrown. | High | Functional / Positive | ✅ PASS | - |
| TC-05 | Borrow a book when no copies are available | REQ-2 | Library has `Book("Clean Code", "9780132350884", "Martin", 0)` (0 copies). Member ID 2 is registered. | 1. `library.borrowBook(2, "9780132350884");` | An `IllegalStateException` is thrown with message "No copies available". Available copies remain 0. | High | Negative / Functional | ❌ FAIL | #24 |
| TC-06 | Return a book currently on loan | REQ-3 | Member ID 1 has borrowed `Book("Clean Code", "9780132350884")`. The due date is today. | 1. `library.returnBook(1, "9780132350884");` <br> 2. Check available copies. | Return succeeds. Available copies increase by 1. Fine calculated is 0.0. | High | Functional / Positive | ✅ PASS | - |
| TC-07 | Return a book not on loan by that member | REQ-3 | Member ID 2 has not borrowed any books. Library has `Book("Clean Code", "9780132350884")`. | 1. `library.returnBook(2, "9780132350884");` | An `IllegalStateException` is thrown with message "Book not on loan by this member". Catalog unchanged. | Medium | Negative / Functional | ❌ FAIL | #25 |
| TC-08 | Member borrowing at the allowed limit | REQ-4 | Library has 5 different books with copies available. Member ID 1 has a borrowing limit of 5 and currently has 4 books borrowed. | 1. `library.borrowBook(1, "ISBN-001");` (5th book) <br> 2. Check member's borrowed count. | Borrow succeeds. Member now has 5 books borrowed. No exception thrown. | Medium | Functional / Positive | ✅ PASS | - |
| TC-09 | Member borrowing beyond the allowed limit | REQ-4 | Member ID 1 has a borrowing limit of 5 and currently has 5 books borrowed. | 1. `library.borrowBook(1, "ISBN-999");` (6th book) | An `IllegalStateException` is thrown with message "Borrowing limit exceeded". Member still has exactly 5 books borrowed. | High | Negative / Functional | ❌ FAIL | #26 |
| TC-10 | Fine calculation for zero days overdue | REQ-5 | Member ID 1 returns a book exactly on the due date. | 1. `library.returnBook(1, "9780132350884");` <br> 2. Check the fine returned. | Fine is `0.0`. No penalty applied. | Low | Functional / Positive | ✅ PASS | - |
| TC-11 | Fine calculation for a mid-range overdue period | REQ-5 | Member ID 1 returns a book that is 5 days overdue. (Assume fine is Rs. 10/day). | 1. `library.returnBook(1, "9780132350884");` <br> 2. Check the fine returned. | Fine is `50.0` (5 days × Rs. 10). | Medium | Functional / Positive | ❌ FAIL | #27 |
| TC-12 | Fine calculation at an overdue-tier boundary | REQ-5 | Member ID 1 returns a book that is exactly 7 days overdue (boundary where fine rate might change, e.g., Rs. 10/day for ≤7 days, Rs. 20/day for >7 days). | 1. `library.returnBook(1, "9780132350884");` <br> 2. Check the fine returned. | Fine is calculated correctly at the lower tier (Rs. 70.0 if Rs. 10/day). Boundary condition is handled correctly. | Medium | Functional / Positive | ⚠️ SKIPPED | - |
| TC-13 | Register a new member successfully | REQ-6 | System has no members registered yet. | 1. `Member member = new Member(1, "Ali Khan", "ali@email.com");` <br> 2. `library.registerMember(member);` <br> 3. Check member count. | Member is registered successfully. Member count becomes 1. No exception thrown. | High | Functional / Positive | ✅ PASS | - |

---

## Summary
- **Total Test Cases:** 13
- **Passed:** 6 (TC-01, TC-04, TC-06, TC-08, TC-10, TC-13)
- **Failed:** 6 (TC-02, TC-03, TC-05, TC-07, TC-09, TC-11)
- **Skipped:** 1 (TC-12 - Fine calculation not implemented)
- **Negative Test Cases:** 5 (TC-02, TC-03, TC-05, TC-07, TC-09)
- **Functional Coverage:** Book addition, borrowing, returning, limit enforcement, fine calculation, and member registration.