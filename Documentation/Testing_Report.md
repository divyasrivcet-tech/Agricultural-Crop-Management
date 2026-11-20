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

### Health API Connectivity Test

- **Module:** Health
- **Input:** GET http://localhost:8080/api/health
- **Expected Output:** JSON response showing database connected status and database name.
- **Actual Output:** HTML Staff Login page was returned instead of the expected JSON response.
- **Result:** FAIL
- **Remarks:** Investigate API routing and Spring Security configuration.

### Valid Farmer Creation Test

- **Module:** Farmer
- **Input:** Valid farmer name, phone number, village, district and address.
- **Expected Output:** Farmer should be saved and returned with a generated ID.
- **Actual Output:** Not recorded.
- **Result:** NOT EXECUTED

### Farmer Name Validation Test

- **Module:** Farmer
- **Input:** Farmer form/API request with blank name.
- **Expected Output:** HTTP 400 validation response.
- **Actual Output:** Not recorded.
- **Result:** NOT EXECUTED

### Farmer Deletion Constraint Test

- **Module:** Farmer
- **Input:** Delete a farmer who is associated with an existing field.
- **Expected Output:** HTTP 409 conflict response; linked data should be protected.
- **Actual Output:** Not recorded.
- **Result:** NOT EXECUTED

### Valid Field Creation Test

- **Module:** Field
- **Input:** Existing farmer ID, field name, location and positive area in acres.
- **Expected Output:** Field should be created and associated with the selected farmer.
- **Actual Output:** Not recorded.
- **Result:** NOT EXECUTED

### Field Area Validation Test

- **Module:** Field
- **Input:** Field area equal to zero or a negative number.
- **Expected Output:** HTTP 400 validation response.
- **Actual Output:** Not recorded.
- **Result:** NOT EXECUTED

### Valid Crop Creation Test

- **Module:** Crop
- **Input:** Valid crop name and crop type.
- **Expected Output:** Crop should be saved and returned with a generated ID.
- **Actual Output:** Not recorded.
- **Result:** NOT EXECUTED

### Crop Deletion Constraint Test

- **Module:** Crop
- **Input:** Delete a crop already associated with a yield record.
- **Expected Output:** HTTP 409 conflict response; referenced crop should not be deleted.
- **Actual Output:** Not recorded.
- **Result:** NOT EXECUTED

### Season Creation Test

- **Module:** Season
- **Input:** Season name, valid start date and end date.
- **Expected Output:** Season should be created successfully.
- **Actual Output:** Not recorded.
- **Result:** NOT EXECUTED

### Season Date Validation Test

- **Module:** Season
- **Input:** End date earlier than start date.
- **Expected Output:** HTTP 400 validation response.
- **Actual Output:** Not recorded.
- **Result:** NOT EXECUTED

### Yield Record Creation Test

- **Module:** Yield
- **Input:** Existing field ID, crop ID, season ID and non-negative yield in kg.
- **Expected Output:** Yield record should be saved with the correct relationships.
- **Actual Output:** Not recorded.
- **Result:** NOT EXECUTED

### Yield Value Validation Test

- **Module:** Yield
- **Input:** Negative yield quantity.
- **Expected Output:** HTTP 400 validation response.
- **Actual Output:** Not recorded.
- **Result:** NOT EXECUTED

### Record Update Test

- **Module:** All CRUD modules
- **Input:** Existing record ID and updated valid field values.
- **Expected Output:** Updated values should be returned and displayed.
- **Actual Output:** Not recorded.
- **Result:** NOT EXECUTED

### Invalid Record ID Test

- **Module:** All CRUD modules
- **Input:** A non-existing record ID.
- **Expected Output:** HTTP 404 response.
- **Actual Output:** Not recorded.
- **Result:** NOT EXECUTED

### Frontend Navigation and Refresh Test

- **Module:** Frontend
- **Input:** Switch between farmer, field, crop, season and yield module tabs; refresh the page.
- **Expected Output:** Correct module content and data should be displayed.
- **Actual Output:** Not recorded.
- **Result:** NOT EXECUTED

### Python Yield Analysis Test

- **Module:** Python Data Analysis
- **Input:** Valid exported CSV file containing the required analysis columns.
- **Expected Output:** Statistics should be printed and a crop chart should be saved when the crop column exists.
- **Actual Output:** Not recorded.
- **Result:** NOT EXECUTED

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



