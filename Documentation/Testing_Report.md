# Software Testing Report
## Agriculture Crop Management and Yield Analysis System

## 1. Objective

To verify the functional correctness, validation, database operations and data analysis features of the application.

## 2. Testing Methodology

Testing includes functional testing, input validation, CRUD testing, API testing, frontend testing and Python data analysis testing.

## 3. Test Results Summary

| Item | Status |
|---|---|
| Total test cases | 16 |
| Passed | 0 confirmed |
| Failed | 1 observed |
| Not executed | 15 |

Note: Results reflect the evidence currently available. The summary must be updated after further test execution.

## 4. Detailed Test Cases

| Test Name | Module | Input | Expected Output | Actual Output | Result | Remarks |
|---|---|---|---|---|---|---|
| Health API Connectivity Test | Health | GET http://localhost:8080/api/health | JSON response showing database connected status and database name. | HTML Staff Login page was returned instead of the expected JSON response. | FAIL | Investigate API routing and Spring Security configuration. |
| Valid Farmer Creation Test | Farmer | Valid farmer name, phone number, village, district and address. | Farmer should be saved and returned with a generated ID. | Not recorded. | NOT EXECUTED |  |
| Farmer Name Validation Test | Farmer | Farmer form/API request with blank name. | HTTP 400 validation response. | Not recorded. | NOT EXECUTED |  |
| Farmer Deletion Constraint Test | Farmer | Delete a farmer who is associated with an existing field. | HTTP 409 conflict response; linked data should be protected. | Not recorded. | NOT EXECUTED |  |
| Valid Field Creation Test | Field | Existing farmer ID, field name, location and positive area in acres. | Field should be created and associated with the selected farmer. | Not recorded. | NOT EXECUTED |  |
| Field Area Validation Test | Field | Field area equal to zero or a negative number. | HTTP 400 validation response. | Not recorded. | NOT EXECUTED |  |
| Valid Crop Creation Test | Crop | Valid crop name and crop type. | Crop should be saved and returned with a generated ID. | Not recorded. | NOT EXECUTED |  |
| Crop Deletion Constraint Test | Crop | Delete a crop already associated with a yield record. | HTTP 409 conflict response; referenced crop should not be deleted. | Not recorded. | NOT EXECUTED |  |
| Season Creation Test | Season | Season name, valid start date and end date. | Season should be created successfully. | Not recorded. | NOT EXECUTED |  |
| Season Date Validation Test | Season | End date earlier than start date. | HTTP 400 validation response. | Not recorded. | NOT EXECUTED |  |
| Yield Record Creation Test | Yield | Existing field ID, crop ID, season ID and non-negative yield in kg. | Yield record should be saved with the correct relationships. | Not recorded. | NOT EXECUTED |  |
| Yield Value Validation Test | Yield | Negative yield quantity. | HTTP 400 validation response. | Not recorded. | NOT EXECUTED |  |
| Record Update Test | All CRUD modules | Existing record ID and updated valid field values. | Updated values should be returned and displayed. | Not recorded. | NOT EXECUTED |  |
| Invalid Record ID Test | All CRUD modules | A non-existing record ID. | HTTP 404 response. | Not recorded. | NOT EXECUTED |  |
| Frontend Navigation and Refresh Test | Frontend | Switch between farmer, field, crop, season and yield module tabs; refresh the page. | Correct module content and data should be displayed. | Not recorded. | NOT EXECUTED |  |
| Python Yield Analysis Test | Python Data Analysis | Valid exported CSV file containing the required analysis columns. | Statistics should be printed and a crop chart should be saved when the crop column exists. | Not recorded. | NOT EXECUTED |  |
## 5. Evidence to Capture

Save genuine screenshots in `Documentation/Evidence/Project_Run/`.

1. Maven build completion.
2. Application opened at localhost.
3. Database health response.
4. Farmer CRUD operations.
5. Field CRUD operations.
6. Crop CRUD operations.
7. Season CRUD operations.
8. Yield record CRUD operations.
9. PostgreSQL table contents.
10. Python console summary and generated chart.

## 6. Automated Smoke-Test Helper

The `scripts/test-api.ps1` helper should only be run after inspecting its contents and confirming that it uses a development/test database. It creates temporary records and removes them. Do not run it against production or important project data without reviewing its operations.

## 7. Sign-off

- Tester:
- Test date:
- Application version / commit:
- Passed:
- Failed:
- Not executed:
- Defects and corrective actions:
- Reviewer:

## 8. Conclusion

The test plan covers the major functional modules and data analysis features of the Agriculture Crop Management and Yield Analysis System.

One observed health API response did not match the expected output. The remaining test cases require execution and evidence collection before final testing sign-off.



