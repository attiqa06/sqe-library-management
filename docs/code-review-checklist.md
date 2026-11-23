# Code Review Checklist (LibraryHub)

## Correctness
- [ ] Does the code do what the PR description says, including edge cases (null, empty, zero, one item)?
- [ ] Are boundary conditions right (`>` vs `>=`, off-by-one, division by zero)?
- [ ] Are errors handled with the right exception type and a clear message?

## Tests
- [ ] Is there at least one new or updated JUnit test for the change?
- [ ] Do the tests assert real behaviour, not just that the code ran?
- [ ] Is each edge case tested (empty list, one member, invalid input)?

## Readability
- [ ] Are names clear and consistent with the rest of the code?
- [ ] Does every public method have a short Javadoc comment?
- [ ] Are there no magic numbers, dead code or unused variables?

## Design
- [ ] Does the change fit the existing structure (Library, Book, LibraryMember)?
- [ ] Is there any duplicated logic that should be shared?
- [ ] Does each method do one clear job?

## Risk
- [ ] Are there security problems (hardcoded secrets, unvalidated input)?
- [ ] Is performance acceptable (no needless nested loops over large lists)?
- [ ] Does the change break existing callers or public method signatures?


## Lessons Learned (Retrospective)

**Most valuable checklist item:** "Are boundary conditions right (`>` vs `>=`, off-by-one, division by zero)?" (Correctness). It caught the integer division in `borrowPercentile`, which made the result always 0 or 100, and the crash on an empty library. SonarCloud showed 0 new issues, so only the review found it.

**Other items that paid off:**
- Tests: reviewing for missing edge cases led to three tests (empty library, single member, normal case) with 100% coverage on new code.
- Readability and design: the unclear names, the magic number 100 and the duplicated loop were all found by reading the code, not by a tool.

**Item to add next time:** "Is the exact meaning of the result documented (for example, whether ties count) and does a test confirm it?" The question about equal loan counts only came up by chance and should be a standing checklist point.

**Other lesson:** Automated checks passed on the flawed code, so reviews and static analysis catch different things and need to be used together.
      
