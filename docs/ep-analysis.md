# Equivalence Partitioning Analysis — LibraryHub

This document records the Equivalence Partitioning (EP) design for three
LibraryHub inputs: the overdue-fine tier, the per-member borrow limit, and
the book ISBN field. Each table lists the classes, the input range each
class covers, one representative value chosen per class, and the expected
system behaviour.

## 1. `fineTier(daysOverdue)` — Worked Example

| # | Class | Range | Representative Value | Valid/Invalid | Expected Result |
|---|-------|-------|----------------------|----------------|------------------|
| 1 | Invalid (negative) | days < 0 | -3 | Invalid | Throws `IllegalArgumentException` |
| 2 | None | 0 | 0 | Valid | "None" |
| 3 | Low | 1–7 | 4 | Valid | "Low" |
| 4 | Medium | 8–14 | 10 | Valid | "Medium" |
| 5 | High | 15–30 | 20 | Valid | "High" |
| 6 | Severe | 31+ | 45 | Valid | "Severe" (account suspended) |

## 2. Borrow Limit — `canBorrow(memberId)` / `borrowBook(memberId, isbn)`

**Business rule:** a member may have between 0 and 5 books on loan at once.

| # | Class | Range | Representative Value | Valid/Invalid | Expected Result |
|---|-------|-------|----------------------|----------------|------------------|
| 1 | Within limit | 0–5 books currently on loan | 3 (borrowing a 4th) | Valid | Borrow succeeds |
| 2 | At/above limit | 6+ books currently on loan | 5 (attempting a 6th) | Invalid | Borrow rejected / `ValueError`-style exception |

*Note:* the representative for class 1 should also include the edge case
of a member at exactly 5 books borrowing their 5th (still within the
0–5 range) if you want an extra valid data point — but per the lab spec
only one representative per class is required, so 3→4th book (class 1)
and 5→6th book (class 2) are sufficient.

## 3. ISBN Field — `validateIsbn(isbn)`

**Business rule:** exactly 13 numeric digits; no letters or symbols.

| # | Class | Example Value | Valid/Invalid | Expected Result |
|---|-------|----------------|----------------|------------------|
| 1 | Valid 13-digit ISBN | `"9780132350884"` | Valid | Accepted |
| 2 | Empty string | `""` | Invalid | Rejected |
| 3 | Too-short string | `"97801323"` (8 digits) | Invalid | Rejected |
| 4 | Contains letters/symbols | `"97801323508X"` or `"978-0132350"` | Invalid | Rejected |
| 5 | Too-long string | `"97801323508842"` (14 digits) | Invalid | Rejected |

*(Classes 1–4 satisfy the "at least 4 classes" requirement; class 5 is
added for stronger coverage of the length constraint.)*

## Limitation of Equivalence Partitioning

EP is effective at cutting the number of test cases while still exercising
every distinct behaviour the system should exhibit, but it treats every
value inside a class as equally likely to reveal a defect. In practice,
bugs cluster at the **edges** of classes — e.g. `daysOverdue = 7` vs. `8`,
or a member borrowing their exact 5th vs. 6th book — and EP's single
"typical" representative per class (4, 10, 20, 3, etc.) will not catch an
off-by-one error at those boundaries. This gap is intentionally left open
here and closed in Lab 6 using Boundary Value Analysis (BVA), which
specifically targets the minimum, maximum, and just-inside/just-outside
values of each class.
