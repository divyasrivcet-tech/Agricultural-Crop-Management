# Testing Plan and Evidence Status

## Purpose

This document defines checks for the delivered application. It is a test plan, not a claim that these tests have already been executed. Fill the Actual Result and Status columns only after running the project.

## Functional test cases

| ID | Module | Test | Expected result | Actual result / status |
|---|---|---|---|---|
| TC-01 | Health | Call `GET /api/health` with database running | Connected status and database name returned | Pending execution |
| TC-02 | Farmer | Add valid farmer | New farmer returned with generated ID | Pending execution |
| TC-03 | Farmer | Submit blank farmer name | HTTP 400 validation response | Pending execution |
| TC-04 | Farmer | Delete farmer linked to a field | HTTP 409 conflict response | Pending execution |
| TC-05 | Field | Add field with valid farmer and positive area | Field created | Pending execution |
| TC-06 | Field | Submit zero/negative area | HTTP 400 validation response | Pending execution |
| TC-07 | Crop | Add valid crop | Crop created | Pending execution |
| TC-08 | Crop | Delete crop used by yield record | HTTP 409 conflict response | Pending execution |
| TC-09 | Season | Add valid date range | Season created | Pending execution |
| TC-10 | Season | End date before start date | HTTP 400 validation response | Pending execution |
| TC-11 | Yield | Add valid linked yield record | Yield record created | Pending execution |
| TC-12 | Yield | Submit negative yield | HTTP 400 validation response | Pending execution |
| TC-13 | All | Update an existing record | Updated values returned and displayed | Pending execution |
| TC-14 | All | Request unknown ID | HTTP 404 response | Pending execution |
| TC-15 | Frontend | Switch between module tabs and refresh | Correct module data is displayed | Pending execution |
| TC-16 | Python | Run analysis with valid exported CSV | Statistics printed; crop chart saved when crop column exists | Pending execution |

## Automated smoke-test helper

Run `scripts/test-api.ps1` only after PostgreSQL and the application are running. It creates temporary records and removes them in dependency-safe order. Inspect the script and use a development/test database.

## Evidence to capture

Save genuine screenshots in `Documentation/Evidence/Project_Run/`:

1. Maven build completion.
2. Application opened at localhost.
3. Database health response.
4. Farmer CRUD.
5. Field CRUD.
6. Crop CRUD.
7. Season CRUD.
8. Yield record CRUD.
9. PostgreSQL table contents.
10. Python console summary and generated chart.

## Sign-off

- Tester:
- Test date:
- Application version / commit:
- Passed:
- Failed:
- Defects and corrective actions:
- Reviewer:
