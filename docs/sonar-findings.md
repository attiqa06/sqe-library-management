# Sonar Findings (Lab 10)

Quality Gate (before): Failed. Reliability rating C and 0% coverage.
SonarCloud found 4 issues (all Reliability, Medium). Fewer than 8 were found, so all are listed.

| # | Rule | File / Line | Severity | Risk |
|---|------|-------------|----------|------|
| 1 | java:S4973 Strings should be compared using equals() | Library.java L147 | Medium | == compares object references, so equal strings can be treated as different. |
| 2 | java:S1764 Identical operands on both sides of == | Library.java L171 | Medium | The condition is always true, so it is a logic mistake that hides a bug. |
| 3 | java:S2201 Return value of trim() ignored | Library.java L175 | Medium | Strings are immutable, so the call does nothing. |
| 4 | java:S2175 List<Integer> cannot contain a String | Library.java L179 | Medium | remove(String) never matches, so the member is never removed. |
