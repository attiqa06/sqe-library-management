# Boundary Value Analysis — LibraryHub

This document records the Boundary Value Analysis (BVA) design for
`fineTier(daysOverdue)`, complementing the Equivalence Partitioning done
in `docs/ep-analysis.md` (Lab 5). For each boundary between two classes,
the table below lists the boundary value itself plus the values
immediately below and above it (value-1, value, value+1), and the
expected `fineTier()` result at each.

## 1. `fineTier(daysOverdue)` — Boundary Pairs

**Cut-offs in the implementation:** `daysOverdue < 0` → throws
`IllegalArgumentException`; `0` → `"None"`; `1–7` → `"Low"`; `8–14` →
`"Medium"`; `15–30` → `"High"`; `31+` → `"Severe"`.

| # | Boundary | value-1 | value | value+1 | Expected @ value-1 | Expected @ value | Expected @ value+1 |
|---|----------|---------|-------|---------|----------------------|--------------------|----------------------|
| 1 | Domain edge — negative / 0 | -1 | 0 | 1 | Throws `IllegalArgumentException` | `"None"` | `"Low"` |
| 2 | None / Low (0 → 1) | 0 | 1 | 2 | `"None"` | `"Low"` | `"Low"` |
| 3 | Low / Medium (7 → 8) | 7 | 8 | 9 | `"Low"` | `"Medium"` | `"Medium"` |
| 4 | Medium / High (14 → 15) | 14 | 15 | 16 | `"Medium"` | `"High"` | `"High"` |
| 5 | High / Severe (30 → 31) | 30 | 31 | 32 | `"High"` | `"Severe"` | `"Severe"` |

*(Boundary 1 doubles as the domain edge required by the lab spec — it is
the only boundary where `value-1` falls in an **invalid** class rather
than an adjacent valid one.)*

## Coverage Note

Five boundaries × three values each gives 15 candidate test cases,
comfortably clearing the "at least 12 parametrized cases, including the
domain edge -1, 0, 1" requirement for Task 2. Row 1 reuses the domain
edge already identified in the EP analysis; rows 2–5 are the four
interior tier cut-offs (`0/1`, `7/8`, `14/15`, `30/31`) named in the lab
manual.

## Next Steps (Task 2)

`J_test_fine_tier_bva.java` (Task 2) will parametrize exactly these 15
`{daysOverdue, expected}` pairs — one `@Test`/parametrized case per cell
in the table above — and any boundary that fails is a genuine off-by-one
defect in `fineTier()` to be fixed and re-verified.
