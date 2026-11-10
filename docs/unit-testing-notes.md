# Unit Testing Notes — LibraryHub (Lab 7)

How the pytest ideas in the Lab 7 manual map onto this Java/JUnit 4/Maven project.

| pytest | This repo (JUnit 4 + Maven) |
|---|---|
| `@pytest.fixture` (function scope) | `@Before` method — runs before every test |
| `@pytest.fixture(scope="module")` | `@BeforeClass` static method — runs once per test class |
| `@pytest.mark.parametrize(..., ids=)` | `@RunWith(Parameterized.class)` + `@Parameters(name = "{0}")` |
| `mocker.patch("builtins.open")` | Mockito `spy(...)` / `mock(Writer.class)` on the `openWriter()` seam |
| `pytest.ini` (`testpaths`, `python_files`) | `pom.xml` (`testSourceDirectory`, surefire `<includes>`) |
| `pytest -v` / `--tb=short` | `mvn test` output vs `-DtrimStackTrace` (see below) |

## 1. Fixture scopes

* **Function scope (`@Before`)** — use when a test *changes* the object (borrowing
  books changes `availableCopies`). A fresh `Library` per test keeps tests
  independent of order. Used in `J_test_borrow_limit`, `J_test_total_available_copies`,
  `J_test_export_catalog`, `J_test_borrow_book_sweep`.
* **Class/"module" scope (`@BeforeClass`)** — use when setup is repeated, identical
  and **read-only** (the ISBN pool in `J_test_borrow_limit`). Never share anything a
  test mutates.

## 2. Verbose vs. short failure output

Run both and paste your real output below.

```
mvn test
```
D:\5th Semester\Software Quality Engineering\Lab\lab 02\sqe-library-management>mvn test 

[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.117 s -- in library.J_test_total_available_copies
[INFO] Running library.J_test_validate_isbn
[INFO] Tests run: 10, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.036 s -- in library.J_test_validate_isbn
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 49, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.117 s -- in library.J_test_total_available_copies
[INFO] Running library.J_test_validate_isbn
[INFO] Tests run: 10, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.036 s -- in library.J_test_validate_isbn
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 49, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------

```
mvn test -DtrimStackTrace=true
```
D:\5th Semester\Software Quality Engineering\Lab\lab 02\sqe-library-management>mvn -DtrimStackTrace=true 

[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/NoGoalSpecifiedException


```
mvn test -DtrimStackTrace=false
```
D:\5th Semester\Software Quality Engineering\Lab\lab 02\sqe-library-management>mvn -DtrimStackTrace=false

[INFO] ------------------------------------------------------------------------
[ERROR] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/NoGoalSpecifiedException

**When to use which:** use the per-test summary and trimmed traces day to day and in CI logs
(quick to scan). Switch to full traces when the trimmed one doesn't show the
real cause, e.g. an exception thrown deep inside a library or a Mockito setup error.

## 3. Test configuration (the `pytest.ini` equivalent)

`pom.xml` holds it: `testSourceDirectory` = `test`, and the surefire `<includes>`
list matches the repo's `J_test_*.java` naming. Lab 14's CI pipeline will run
`mvn test` and pick this up automatically.
