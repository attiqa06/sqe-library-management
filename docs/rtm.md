# Requirements Traceability Matrix (RTM)

**Version:** 1.0  
**Date:** 2026-09-05  
**Author:** [Your Name]  
**Project:** LibraryHub - Library Management System

---

## Requirements List

| Requirement ID | Description |
| :--- | :--- |
| **REQ-1** | The system shall reject books with a duplicate ISBN and reject malformed ISBNs. |
| **REQ-2** | The system shall allow borrowing only if copies are available. |
| **REQ-3** | The system shall allow returning only if the book is currently on loan by that member. |
| **REQ-4** | The system shall enforce a maximum borrowing limit per member. |
| **REQ-5** | The system shall calculate fines correctly based on overdue days and tiered rates. |
| **REQ-6** | The system shall allow registration of new members. |

---

## Traceability Matrix

| Requirement ID | Test Case IDs | Coverage Status |
| :--- | :--- | :--- |
| **REQ-1** | TC-01, TC-02, TC-03 | ✅ Covered |
| **REQ-2** | TC-04, TC-05 | ✅ Covered |
| **REQ-3** | TC-06, TC-07 | ✅ Covered |
| **REQ-4** | TC-08, TC-09 | ✅ Covered |
| **REQ-5** | TC-10, TC-11, TC-12 | ✅ Covered |
| **REQ-6** | **None** | ❌ **UNTRACED - Missing test case(s)** |

---

## Gap Analysis

- **REQ-6** currently has **zero** linked test cases. This is a critical gap because member registration is a fundamental prerequisite for borrowing.
- To close this gap, a new test case (**TC-13**) has been added to `docs/test-cases.md` to verify member registration functionality.

---

## Updated Coverage

| Requirement ID | Test Case IDs | Coverage Status |
| :--- | :--- | :--- |
| **REQ-1** | TC-01, TC-02, TC-03 | ✅ Covered |
| **REQ-2** | TC-04, TC-05 | ✅ Covered |
| **REQ-3** | TC-06, TC-07 | ✅ Covered |
| **REQ-4** | TC-08, TC-09 | ✅ Covered |
| **REQ-5** | TC-10, TC-11, TC-12 | ✅ Covered |
| **REQ-6** | **TC-13** | ✅ Covered |

---

## Summary

- **Total Requirements:** 6
- **Requirements with Test Cases:** 6 (100% coverage)
- **Total Test Cases:** 13 (original 12 + TC-13 to cover REQ-6)
- **Status:** All requirements are now traceable to at least one test case.