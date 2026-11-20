# Earned Value Management (EVM) Analysis Report

## 1. Objective
To demonstrate project schedule and effort performance using Earned Value Management calculations.

## 2. Input and Assumptions

IMPORTANT: The following values are illustrative assumptions for academic demonstration. They are not verified actual project expenditure or recorded development hours.

| Parameter | Value |
|---|---:|
| Budget at Completion (BAC) | 100 person-hours |
| Planned completion | 80% |
| Actual completion | 70% |
| Actual effort used | 75 person-hours |

## 3. Formulas

- BCWS (Planned Value) = BAC x Planned Completion
- BCWP (Earned Value) = BAC x Actual Completion
- ACWP (Actual Cost) = Actual Effort Used
- Schedule Variance (SV) = BCWP - BCWS
- Cost Variance (CV) = BCWP - ACWP
- Schedule Performance Index (SPI) = BCWP / BCWS
- Cost Performance Index (CPI) = BCWP / ACWP

For this report, cost is represented by person-hours as an effort-based proxy.

## 4. Expected Output

Based on the illustrative input:

- BCWS = 100 x 0.80 = 80 person-hours
- BCWP = 100 x 0.70 = 70 person-hours
- ACWP = 75 person-hours
- SV = 70 - 80 = -10 person-hours
- CV = 70 - 75 = -5 person-hours
- SPI = 70 / 80 = 0.875
- CPI = 70 / 75 = 0.9333

## 5. Actual Calculated Output

| Metric | Formula | Calculated Value |
|---|---|---:|
| BCWS | 100 x 80% | 80 hours |
| BCWP | 100 x 70% | 70 hours |
| ACWP | Actual effort | 75 hours |
| SV | 70 - 80 | -10 hours |
| CV | 70 - 75 | -5 hours |
| SPI | 70 / 80 | 0.875 |
| CPI | 70 / 75 | 0.9333 |

## 6. Result

The illustrative EVM calculation produced:

- Negative Schedule Variance: work progress is behind the planned baseline.
- Negative Cost Variance: more effort was used than the earned effort value.
- SPI below 1: schedule performance is behind plan.
- CPI below 1: effort efficiency is below the planned baseline.

## 7. Conclusion

Earned Value Management helps evaluate schedule and cost performance by comparing planned work, completed work and actual effort.

The calculated values in this report are illustrative academic examples. Actual project performance requires verified project schedule, completion and effort records.

## 8. Reference to Project Size

The separate LOC analysis report records:
- Total source files: 21
- Total physical LOC: 1962
- KLOC: 1.962

LOC measures software size. EVM measures project performance. LOC alone cannot determine BCWS, BCWP or ACWP.
