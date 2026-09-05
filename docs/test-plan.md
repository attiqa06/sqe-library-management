# Test Plan for LibraryHub (LMS)

**Version:** 1.0  
**Date:** 2026-09-05  
**Author:** [Your Name]  
**Project:** LibraryHub - Library Management System

## 1. Introduction
This Test Plan defines the scope, approach, resources, and schedule for testing the LibraryHub module. It covers the core functionality of adding books, borrowing/returning items, and fine calculation. The primary objective is to validate that the system meets its functional requirements and handles error conditions gracefully.

## 2. Test Items
The following LibraryHub Java components will be exercised by this test plan:
- `Library.addBook(Book book)` – adding new books to the catalog.
- `Library.borrowBook(int memberId, String isbn)` – borrowing a book if copies are available.
- `Library.returnBook(int memberId, String isbn)` – returning a book and calculating overdue fines.
- `Member` borrowing limit enforcement (max books allowed via `Member.canBorrow()`).
- `Fine` calculation logic based on overdue days (using `FineCalculator.calculateFine()`).

## 3. Features to be Tested
- **Book Management:** Adding valid books, rejecting duplicate ISBNs, and rejecting malformed ISBNs.
- **Borrowing Logic:** Successful borrows, blocked borrows when no copies remain, and enforcing member borrowing limits.
- **Return & Fine Logic:** Successful returns, rejecting returns for books not on loan, and calculating fines correctly for different overdue periods (zero, mid-range, and boundary tiers).
- **Error Handling:** All negative test cases (e.g., invalid inputs) must raise appropriate exceptions without crashing the system.

## 4. Features Not to be Tested
- **Graphical User Interface (GUI):** LibraryHub is a backend Java module with no web or desktop frontend. Therefore, UI layout, button clicks, and browser compatibility are explicitly out of scope for this test plan.
- **Performance/Load Testing:** Stress-testing the system with thousands of simultaneous borrow requests is deferred to a future performance-testing phase.
- **Database Persistence:** The current implementation uses in-memory data structures; persistent storage (SQL/NoSQL) is not yet implemented and thus not tested.

## 5. Approach
Testing will be conducted manually by executing predefined test cases against the current LibraryHub Java codebase. Testers will use either:
- A **JUnit test class** (e.g., `LibraryTest.java`) with individual `@Test` methods for each scenario, or
- A temporary `Main.java` runner with hardcoded method calls to simulate each test step.
Each test case will be marked as **Pass**, **Fail**, or **Blocked**. Any failures will be immediately reported as GitHub Issues (using the templates from Lab 3) and linked back to the test case row in the test log.

## 6. Pass/Fail Criteria
A test cycle is considered **PASSED** if both of the following conditions are met:
1. **100%** of the planned test cases (12 out of 12) have been executed.
2. **At least 90%** of executed test cases (i.e., 11 out of 12) yield a **Pass** result.
3. **Zero Critical-severity** defects remain open. All Critical and High-severity failures must be fixed and re-tested before sign-off.

## 7. Test Deliverables
Upon completion of this test cycle, the following artifacts will be delivered:
- `docs/test-plan.md` – This document.
- `docs/test-cases.md` – A complete list of 12 test cases with steps and expected results.
- `docs/rtm.md` – A Requirements Traceability Matrix mapping each requirement to its test cases.
- Execution results (Pass/Fail/Blocked) annotated in the test case table.
- GitHub Issues linked to any failed test cases.

## 8. Environmental Needs
- **Hardware:** Standard development machine (PC/Mac) with 8GB+ RAM.
- **Software:** 
  - Java Development Kit (JDK) 11 or higher.
  - IntelliJ IDEA or Eclipse IDE (or any text editor with Java support).
  - Git and a GitHub account.
- **Tools:** GitHub Issues for defect tracking, Markdown for documentation.
- **Test Data:** A predefined list of sample ISBNs, book titles, and member IDs to ensure repeatable tests.

## 9. Schedule
| Activity | Duration |
| :--- | :--- |
| Author Test Plan (Task 1) | 60 min |
| Write 12 Test Cases (Task 2) | 75 min |
| Build Traceability Matrix (Task 3) | 30 min |
| Execute Tests & Log Defects (Task 4) | 35 min |
| **Total** | **3.0 hours** |

## 10. Risks and Contingencies
- **Risk:** The codebase may contain severe hidden defects that block multiple test cases.
  - *Mitigation:* If a Critical defect is found early, we will pause execution, fix it (or skip that test), and continue with others.
- **Risk:** Time constraints may prevent fixing all failures.
  - *Mitigation:* We will triage failures by severity; only Critical and High-priority issues will be fixed in this cycle. Low-priority issues will be deferred.