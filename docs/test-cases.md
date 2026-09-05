# Test Cases for LibraryHub (LMS)

**Version:** 1.0  
**Date:** 2026-09-05  
**Author:** [Your Name]  
**Project:** LibraryHub - Library Management System

---

## Execution Summary

| ID | Title | Result | Linked Issue |
| :--- | :--- | :--- | :--- |
| TC-01 | Add a valid new book | ✅ PASS | - |
| TC-02 | Add a book with duplicate ISBN | ❌ FAIL | #22 |
| TC-03 | Add a book with malformed ISBN | ❌ FAIL | #23 |
| TC-04 | Borrow a book when copies are available | ✅ PASS | - |
| TC-05 | Borrow a book when no copies are available | ❌ FAIL | #24 |
| TC-06 | Return a book currently on loan | ✅ PASS | - |
| TC-07 | Return a book not on loan by that member | ❌ FAIL | #25 |
| TC-08 | Member borrowing at the allowed limit | ✅ PASS | - |
| TC-09 | Member borrowing beyond the allowed limit | ❌ FAIL | #26 |
| TC-10 | Fine calculation for zero days overdue | ✅ PASS | - |
| TC-11 | Fine calculation for a mid-range overdue period | ❌ FAIL | #27 |
| TC-12 | Fine calculation at an overdue-tier boundary | ⚠️ SKIPPED | - |
| TC-13 | Register a new member successfully | ✅ PASS | - |

---

## Summary
- **Total Test Cases:** 13
- **Passed:** 6 (TC-01, TC-04, TC-06, TC-08, TC-10, TC-13)
- **Failed:** 6 (TC-02, TC-03, TC-05, TC-07, TC-09, TC-11)
- **Skipped:** 1 (TC-12 - Fine calculation not implemented)
- **Negative Test Cases:** 5 (TC-02, TC-03, TC-05, TC-07, TC-09)