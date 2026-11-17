## Task 1: Baseline Coverage (Lab 11)

Tool: JaCoCo (via Maven)
Baseline command: `mvn verify` (automated in GitHub Actions)

Baseline results:
- Line coverage: 30% (31/78)
- Branch coverage: 64% (16/41)

## Task 2: Three Weakest Files

Based on the JaCoCo HTML report, the three source files with the lowest coverage are:

1. **Main.java** (0% Lines, 0% Branches)
   - Red lines: Entire class (`main` method, constructor).

2. **Student.java** (0% Lines, 0% Branches)
   - Red lines/branches: All methods (`calculateAverage`, `addScore`, constructor, getters/setters, `getScores`). 

3. **LibraryMember.java** (71% Lines, n/a Branches)
   - Red lines: `getId()` method (L1 missed), `getEmail()` method (L1 missed).
