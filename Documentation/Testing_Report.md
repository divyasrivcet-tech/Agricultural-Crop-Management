# Software Testing Report
## Agriculture Crop Management and Yield Analysis System

## 1. Objective

To verify the functional correctness, validation, database operations and data analysis features of the application.

## 2. Testing Methodology

Testing includes functional testing, input validation, CRUD testing, API testing and frontend testing.

## 3. Test Results Summary

| Item | Status |
|---|---|
| Total test cases | 6 |
| Passed | 5 |
| Failed | 1 |
| Not executed | 0 |

Note: Only test cases with available execution evidence are included in this report.

## 4. Detailed Test Cases

| Test Name | Module | Input | Expected Output | Actual Output | Result | Remarks |
|---|---|---|---|---|---|---|
| Health API Connectivity Test | Health | GET http://localhost:8080/api/health | JSON response showing database connected status and database name. | HTML Staff Login page was returned instead of the expected JSON response. | FAIL | Investigate API routing and Spring Security configuration. |
| Valid Farmer Creation Test | Farmer | Valid farmer details. | Farmer should be saved and returned with a generated ID. | Farmer creation successfully verified during earlier CRUD testing. | PASS |  |
| Farmer Name Validation Test | Farmer | Farmer form with blank name. | Browser should prevent submission when the required name is blank. | Browser displayed "Please fill out this field" for Farmer Name. | PASS |  |
| Valid Crop Creation Test | Crop | Valid crop name and crop type. | Crop should be saved and returned with a generated ID. | Crop creation verified during earlier CRUD testing. | PASS |  |
| Season Creation Test | Season | Season name, valid start date and end date. | Season should be created successfully. | Season CRUD testing previously completed. | PASS |  |
| Record Update Test | Farmer | Existing farmer ID and updated valid details. | Updated values should be returned and displayed. | Farmer update successfully verified during earlier CRUD testing. | PASS |  |
| Frontend Navigation and Refresh Test | Frontend | Switch between farmer, field, crop, season and yield module tabs; refresh the page. | Correct module content and data should be displayed. | Login page, dashboard, module navigation and page refresh were verified in the browser. | PASS |  |

## 5. Evidence to Capture

Save genuine screenshots in `Documentation/Evidence/Project_Run/`.
