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
