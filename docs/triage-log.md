# Triage Meeting Log - LibraryHub Issues

## Date: [Insert Today's Date]

### Issues Under Review:

| Issue # | Title | Severity | Priority | Status |
|---------|-------|----------|----------|--------|
| #1 | Negative available_copies allowed when borrowing books | Critical | High | Open |
| #2 | Duplicate ISBNs accepted in library system | High | Medium | Open |
| #3 | Member can borrow past allowed limit | High | Medium | Open |
| #4 | Incorrect rounding of late fees | Low | Medium | Open |
| #5 | Case-sensitive title search bug | Low | Low | Open |

---

### Ranking (From Highest to Lowest Priority to Fix):

| Rank | Issue # | Title | Reason for Ranking |
|------|---------|-------|-------------------|
| 1 | #1 | Negative available_copies allowed when borrowing books | Critical severity - causes data corruption (negative inventory) |
| 2 | #3 | Member can borrow past allowed limit | High severity - violates business rules, affects fair book distribution |
| 3 | #2 | Duplicate ISBNs accepted in library system | High severity - data integrity issue but no crash |
| 4 | #4 | Incorrect rounding of late fees | Medium priority - affects revenue but low severity |
| 5 | #5 | Case-sensitive title search bug | Low severity and low priority - usability issue only |

---

### Trade-off Analysis (Severity vs Priority):

#### Trade-off 1: Issue #1 (Critical/High) vs Issue #3 (High/Medium)

| Issue | Severity | Priority | Impact |
|-------|----------|----------|--------|
| #1 | Critical | High | Allows negative inventory, corrupts library data |
| #3 | High | Medium | Violates business rules, unfair borrowing |

**Trade-off Decision:**
- Issue #1 takes priority because it allows negative inventory which can corrupt the entire library database
- Issue #3 is important but doesn't corrupt data - it's a business rule violation
- **Conclusion:** Fix Issue #1 first (data integrity), then Issue #3 (business rules)

---

#### Trade-off 2: Issue #2 (High/Medium) vs Issue #4 (Low/Medium)

| Issue | Severity | Priority | Impact |
|-------|----------|----------|--------|
| #2 | High | Medium | Duplicate ISBNs cause book retrieval confusion |
| #4 | Low | Medium | Financial inaccuracy, affects revenue |

**Trade-off Decision:**
- Issue #2 will be fixed before Issue #4
- **Why:** While both are Medium priority, Issue #2 has Higher severity (data integrity). Duplicate ISBNs can cause serious confusion when members try to borrow books. Issue #4 is a mathematical inaccuracy that is low risk but can be deferred.
- **Conclusion:** Fix Issue #2 first, then Issue #4

---

### Issues Marked as "Won't Fix" This Sprint:

**Issue #4 - Incorrect rounding of late fees**
- **Status:** `status:wontfix`
- **Reason:** Low severity issue that affects fine amounts but doesn't break core functionality. Can be deferred to next sprint.

**Issue #5 - Case-sensitive title search bug**
- **Status:** `status:wontfix`
- **Reason:** Low severity and Low priority - this is a usability issue only. Users can work around by typing exact case. Can be deferred to a later sprint.

---

### Final Decision Summary:

| Issue | Title | Decision | Status | Justification |
|-------|-------|----------|--------|---------------|
| #1 | Negative available_copies allowed | Fix Now | In Progress | Critical severity, data corruption |
| #2 | Duplicate ISBNs accepted | Fix Now | In Progress | High severity, data integrity issue |
| #3 | Member can borrow past allowed limit | Fix Now | In Progress | High severity, business rule violation |
| #4 | Incorrect rounding of late fees | Defer | Wontfix | Low severity, can be fixed next sprint |
| #5 | Case-sensitive title search bug | Wontfix | Wontfix | Low impact, usability only |

---

### Sprint Planning Summary:

**Will be fixed in this sprint (3 issues - 60%):**
- ✅ Issue #1: Negative available_copies allowed (Critical/High)
- ✅ Issue #2: Duplicate ISBNs accepted (High/Medium)
- ✅ Issue #3: Member can borrow past allowed limit (High/Medium)

**Deferred to future sprints (2 issues - 40%):**
- ⏳ Issue #4: Incorrect rounding of late fees (Low/Medium)
- ⏳ Issue #5: Case-sensitive title search bug (Low/Low)

---

### Risk Assessment:
- **Critical risks resolved:** Negative inventory bug (Issue #1) will be fixed immediately
- **Medium risks remaining:** Duplicate ISBNs (Issue #2) and borrowing limits (Issue #3) will be fixed within sprint
- **Low risks remaining:** Fine rounding (Issue #4) and search (Issue #5) are deferred

**Overall Assessment:** Core functionality is safe after fixing the 3 priority issues.
