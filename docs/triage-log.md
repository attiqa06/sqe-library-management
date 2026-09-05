# Triage Meeting Log - GradeBook Issues

## Date: [Insert Today's Date]

### Issues Under Review:

| Issue # | Title | Severity | Priority | Status |
|---------|-------|----------|----------|--------|
| #1 | Crash on Class Average Calculation with No Students Enrolled | Critical | High | Open |
| #2 | Negative Scores Accepted for Assignments | High | High | Open |
| #3 | Duplicate Roll Numbers Permitted | High | Medium | Open |
| #4 | Incorrect Rounding of Averages (89.5 → 89) | Low | Medium | Open |
| #5 | Search Function Case-Sensitive | Low | Low | Open |

---

### Ranking (From Highest to Lowest Priority to Fix):

| Rank | Issue # | Title | Reason for Ranking |
|------|---------|-------|-------------------|
| 1 | #1 | Crash on Class Average Calculation with No Students Enrolled | Critical severity - causes application crash, makes software unusable |
| 2 | #2 | Negative Scores Accepted for Assignments | High severity - corrupts data integrity, visible to users |
| 3 | #4 | Incorrect Rounding of Averages (89.5 → 89) | Medium priority - clean fix, affects grade boundaries |
| 4 | #3 | Duplicate Roll Numbers Permitted | High severity but medium priority - data integrity issue but no crash |
| 5 | #5 | Search Function Case-Sensitive | Low severity and low priority - usability issue only |
---

### Trade-off Analysis (Severity vs Priority):

#### Trade-off 1: Issue #1 (Critical/High) vs Issue #2 (High/High)

Both issues have High Priority, but their Severity levels differ:

| Issue | Severity | Priority | Impact |
|-------|----------|----------|--------|
| #1 | Critical | High | Application crash, complete failure of core feature |
| #2 | High | High | Data corruption, incorrect calculations |

**Trade-off Decision:**
- Issue #1 takes priority even though both have High Priority
- **Why:** Critical Severity means the application becomes completely unusable. A crash on a core feature is a showstopper that affects ALL users. Issue #2, while serious (data corruption), doesn't prevent the application from running. Users can temporarily work around the negative score issue by being careful, but they cannot work around a crash.
- **Conclusion:** Fix Issue #1 first, then Issue #2 immediately after.

---

#### Trade-off 2: Issue #3 (High/Medium) vs Issue #4 (Low/Medium)

Both issues have Medium Priority, but their Severity levels differ:

| Issue | Severity | Priority | Impact |
|-------|----------|----------|--------|
| #3 | High | Medium | Data integrity issue, duplicate roll numbers allowed |
| #4 | Low | Medium | Mathematical inaccuracy, affects grade boundaries |

**Trade-off Decision:**
- Issue #4 will be fixed before Issue #3, even though Issue #3 has higher Severity
- **Why:** This is a "quick win" scenario. Issue #4 is a simple one-line change (fixing integer division to proper rounding). It has low risk and can be implemented quickly. Issue #3 requires more complex changes (adding uniqueness validation, database constraints, error handling) and carries higher risk of introducing new bugs. By fixing Issue #4 first, we get a clean, easy victory while planning a proper fix for Issue #3 in the next sprint.
- **Conclusion:** Fix Issue #4 now (quick win), defer Issue #3 to next sprint with proper planning.

---

#### Summary of Final Decision:

| Fix Order | Issue | Reason |
|-----------|-------|--------|
| 1st | #1 | Critical severity, causes crash |
| 2nd | #2 | High severity, data corruption |
| 3rd | #4 | Quick win, low risk, affects grades |
| Deferred | #3 | Complex fix, needs more planning |
| Wontfix | #5 | Low impact, usability only |

---

### Issues Marked as "Won't Fix" This Sprint:

**Issue #5 - Search Function Case-Sensitive**
- **Status:** `status:wontfix`
- **Reason:** This is a usability issue with Low Severity and Low Priority. It doesn't affect core functionality, data integrity, or cause crashes. Can be deferred to next sprint without impacting GradeBook's main features. Users can work around this by typing the correct case.

---

### Final Decision:

We will fix **Issues #1, #2, and #4** in this sprint. Issues #3 and #5 will be deferred (Issue #3 to next sprint, Issue #5 marked as wontfix for now).

---
