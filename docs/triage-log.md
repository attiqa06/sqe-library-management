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

1. **Issue #1** - Crash on Class Average Calculation with No Students Enrolled
2. **Issue #2** - Negative Scores Accepted for Assignments
3. **Issue #4** - Incorrect Rounding of Averages
4. **Issue #3** - Duplicate Roll Numbers Permitted
5. **Issue #5** - Search Function Case-Sensitive

---

### Trade-off Analysis (Severity vs Priority):

**Issue #1 vs Issue #2:**

Both issues are High Priority, but Issue #1 has Critical Severity while Issue #2 has High Severity. 
- **Trade-off:** Issue #1 causes an application crash (Critical Severity), which completely breaks functionality. Even though both are High Priority, the crash must be fixed first because it makes the software unusable. The negative score bug (Issue #2) is still important but data can be manually corrected until the fix is applied.

**Issue #3 vs Issue #4:**

Issue #3 has High Severity but Medium Priority, while Issue #4 has Low Severity but Medium Priority.
- **Trade-off:** Even though Issue #4 is easier to fix and is a clean mathematical fix, Issue #3 is more important because it affects data integrity (duplicate roll numbers). We will fix Issue #4 before Issue #3 because it's a quick win with less risk, but both are Medium Priority.

---

### Issues Marked as "Won't Fix" This Sprint:

**Issue #5 - Search Function Case-Sensitive**
- **Status:** `status:wontfix`
- **Reason:** This is a usability issue with Low Severity and Low Priority. It doesn't affect core functionality, data integrity, or cause crashes. Can be deferred to next sprint without impacting GradeBook's main features. Users can work around this by typing the correct case.

---

### Final Decision:

We will fix **Issues #1, #2, and #4** in this sprint. Issues #3 and #5 will be deferred (Issue #3 to next sprint, Issue #5 marked as wontfix for now).

---
