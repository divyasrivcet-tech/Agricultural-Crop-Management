# SOFTWARE REQUIREMENTS SPECIFICATION (SRS)

## Agriculture Crop Management and Yield Analysis System

**Version:** 1.0  
**Date:** 18 November 2026  
**Project Type:** Web-Based Application

---

## 1. Introduction

### 1.1 Purpose

The purpose of this Software Requirements Specification (SRS) is to describe the functional and non-functional requirements of the Agriculture Crop Management and Yield Analysis System.

The system provides a centralized web application for managing farmers, agricultural fields, crops, seasons and yield records. It also provides individual staff registration, authentication and logout facilities.

### 1.2 Project Scope

The Agriculture Crop Management and Yield Analysis System is designed to simplify agricultural data management through a browser-based application.

The system provides the following major modules:

1. Staff Registration and Authentication
2. Farmer Management
3. Field Management
4. Crop Management
5. Season Management
6. Yield Record Management
7. Yield Data Analysis Support

Authorized staff can create, view, update and delete agricultural records through the web interface.

### 1.3 Intended Audience

This document is intended for:
- Project developers
- Project guides and evaluators
- College students
- Staff members using the application
- Future system maintainers

### 1.4 Definitions

| Term | Description |
|---|---|
| SRS | Software Requirements Specification |
| CRUD | Create, Read, Update and Delete |
| API | Application Programming Interface |
| JDBC | Java Database Connectivity |
| BCrypt | Password hashing algorithm |
| DBMS | Database Management System |
| UML | Unified Modeling Language |

---

## 2. Overall Description

### 2.1 Product Perspective

The application follows a web-based client-server architecture.

The frontend is developed using HTML, CSS and JavaScript. The backend is developed using Java and Spring Boot. Spring JDBC is used to communicate with the PostgreSQL database.

Staff members access the application through a web browser.

### 2.2 Product Functions

The major functions are:

- Register a new staff account.
- Authenticate registered staff members.
- Log out securely.
- Add and manage farmer information.
- Maintain agricultural field details.
- Maintain crop information.
- Manage agricultural seasons.
- Record and manage crop yield information.
- Retrieve agricultural records from PostgreSQL.
- Support yield-related analysis through database queries and project analysis resources.

### 2.3 User Classes

**Staff User**

- Creates an individual staff account.
- Logs in using username and password.
- Accesses the agricultural management dashboard.
- Performs permitted CRUD operations.
- Logs out after completing the work.

All authenticated staff accounts currently use the STAFF role.

### 2.4 Operating Environment

**Software Requirements**

| Component | Technology |
|---|---|
| Operating System | Windows / compatible OS |
| Programming Language | Java 21 |
| Backend Framework | Spring Boot 3.5.6 |
| Frontend | HTML, CSS, JavaScript |
| Database | PostgreSQL |
| Database Access | Spring JDBC |
| Security | Spring Security |
| Password Hashing | BCrypt |
| Build Tool | Maven |
| Browser | Modern web browser |
| Analysis Support | Python, Pandas and Matplotlib |

### 2.5 Design Constraints

- Java 21 is required for the application configuration.
- PostgreSQL must be available and configured.
- Database connection details must be configured before application startup.
- Staff authentication is required to access protected application resources.
- Each staff account must have a unique username and email address.
- Passwords must be stored as BCrypt hashes rather than plain text.

### 2.6 Assumptions and Dependencies

- Users have access to a compatible web browser.
- The PostgreSQL database is available.
- The Spring Boot application can connect to the configured database.
- Staff members provide valid registration and login details.
- Internet access is required when the application is hosted remotely.

---

## 3. Functional Requirements

### FR-01: Staff Registration

The system shall allow new staff members to create individual accounts.

Registration fields:
- Full Name
- Username
- Email
- Password
- Confirm Password

Validation requirements:
- Required fields must not be empty.
- Email must follow a valid email format.
- Password must contain at least eight characters.
- Password and confirmation must match.
- Duplicate usernames and email addresses must be rejected.
- Passwords must be stored using BCrypt hashing.

### FR-02: Staff Login

- The system shall provide a staff login page.
- Staff members shall enter their username and password.
- The system shall retrieve account credentials from PostgreSQL.
- Spring Security shall verify the password.
- Valid credentials shall provide access to the dashboard.
- Invalid credentials shall return the user to the login page.

### FR-03: Staff Logout

- The system shall provide a logout option.
- Logout shall terminate the authenticated session.
- The user shall be redirected to the login page.

### FR-04: Farmer Management

The system shall support:
- Adding farmer information.
- Viewing all farmers.
- Viewing an individual farmer by ID.
- Updating farmer details.
- Deleting farmer information.

Farmer information includes farmer name, phone, village and district.

### FR-05: Field Management

The system shall support:
- Adding agricultural fields.
- Viewing all fields.
- Viewing an individual field by ID.
- Updating field information.
- Deleting field information.

Field information includes farmer reference, field name and area in acres.

Field area must be greater than zero.

### FR-06: Crop Management

The system shall support:
- Adding crop details.
- Viewing all crops.
- Viewing an individual crop by ID.
- Updating crop details.
- Deleting crop details.

Crop information includes crop name and crop type.

### FR-07: Season Management

The system shall support:
- Adding agricultural seasons.
- Viewing all seasons.
- Viewing an individual season by ID.
- Updating season details.
- Deleting season details.

Season information includes season name, start date and end date.

The end date must not be earlier than the start date.

### FR-08: Yield Record Management

The system shall support:
- Adding yield records.
- Viewing all yield records.
- Viewing an individual yield record by ID.
- Updating yield records.
- Deleting yield records.

Each yield record shall contain:
- Field reference
- Crop reference
- Season reference
- Yield quantity in kilograms
- Recorded date

The yield quantity must not be negative. New records shall use the current database date.

### FR-09: Data Validation and Error Handling

- The system shall validate mandatory input fields.
- Invalid input shall return an appropriate error response.
- Requests for unavailable records shall return a not-found response.
- Records referenced by other database records shall not be deleted while dependent records exist.
- Database constraint violations shall be handled appropriately.

### FR-10: Database Operations

- The system shall store agricultural records in PostgreSQL.
- The system shall retrieve records using Spring JDBC.
- Foreign keys shall maintain relationships between related agricultural records.
- Database constraints shall help maintain data consistency.

---

## 4. External Interface Requirements

### 4.1 User Interface

The application shall provide:
- Staff login page.
- Staff registration page.
- Agricultural management dashboard.
- Data entry forms.
- Record display tables.
- Update and delete actions.
- Logout button.

### 4.2 Software Interface

**Frontend to Backend**

The JavaScript frontend communicates with the Spring Boot REST API.

**Backend to Database**

Spring JDBC communicates with PostgreSQL to execute database queries.

### 4.3 API Interface

The major application endpoints are:

| Module | API Endpoint | Supported Operations |
|---|---|---|
| Authentication | `/api/auth/register` | POST |
| Authentication | `/api/auth/login` | Spring Security form login |
| Authentication | `/api/auth/logout` | POST |
| Farmers | `/api/farmers` | GET, POST |
| Farmers | `/api/farmers/{id}` | GET, PUT, DELETE |
| Fields | `/api/fields` | GET, POST |
| Fields | `/api/fields/{id}` | GET, PUT, DELETE |
| Crops | `/api/crops` | GET, POST |
| Crops | `/api/crops/{id}` | GET, PUT, DELETE |
| Seasons | `/api/seasons` | GET, POST |
| Seasons | `/api/seasons/{id}` | GET, PUT, DELETE |
| Yield Records | `/api/yield-records` | GET, POST |
| Yield Records | `/api/yield-records/{id}` | GET, PUT, DELETE |

---

## 5. Database Requirements

The application uses PostgreSQL with the following main tables.

### 5.1 Staff Users

| Column | Description |
|---|---|
| staff_id | Primary key |
| full_name | Staff full name |
| username | Unique username |
| email | Unique email address |
| password_hash | BCrypt password hash |
| created_at | Account creation timestamp |

### 5.2 Farmers

| Column | Description |
|---|---|
| farmer_id | Primary key |
| farmer_name | Farmer name |
| phone | Contact number |
| village | Village |
| district | District |

### 5.3 Fields

| Column | Description |
|---|---|
| field_id | Primary key |
| farmer_id | Reference to farmer |
| field_name | Field name |
| area_acres | Field area in acres |

### 5.4 Crops

| Column | Description |
|---|---|
| crop_id | Primary key |
| crop_name | Crop name |
| crop_type | Crop category |

### 5.5 Seasons

| Column | Description |
|---|---|
| season_id | Primary key |
| season_name | Season name |
| start_date | Season starting date |
| end_date | Season ending date |

### 5.6 Yield Records

| Column | Description |
|---|---|
| record_id | Primary key |
| field_id | Reference to field |
| crop_id | Reference to crop |
| season_id | Reference to season |
| yield_kg | Yield quantity |
| recorded_date | Record creation date |

### 5.7 Database Relationships

- One farmer can own multiple fields.
- One field can be associated with multiple yield records.
- One crop can be associated with multiple yield records.
- One season can be associated with multiple yield records.
- Staff accounts are maintained separately from agricultural records.

---

## 6. Non-Functional Requirements

### 6.1 Security

- The system shall use Spring Security for authentication.
- Passwords shall be stored as BCrypt hashes.
- Protected resources shall require authentication.
- Login failures shall not grant dashboard access.
- Staff account usernames and email addresses shall be unique.

### 6.2 Performance

- The application should respond to normal user requests within a reasonable time.
- Database queries should retrieve only the required information.
- The application should support routine agricultural record management.

### 6.3 Reliability

- The system shall maintain records in PostgreSQL.
- Database constraints shall prevent invalid relationships.
- Errors shall be handled without intentionally exposing sensitive information.

### 6.4 Usability

- The application shall provide a simple web interface.
- Forms shall use understandable labels.
- Users shall receive appropriate validation and operation messages.
- Navigation shall support the five management modules.

### 6.5 Maintainability

- Backend modules shall be organized into separate controllers.
- Database scripts shall be maintained separately.
- Frontend HTML, CSS and JavaScript shall be maintained as separate resources.
- Maven shall manage project dependencies and builds.

### 6.6 Portability

The application shall be deployable in compatible Java and PostgreSQL environments with suitable configuration.

---

## 7. System Workflow

1. The staff member opens the application.
2. A new user registers or an existing user logs in.
3. Spring Security validates the login credentials.
4. The authenticated staff member accesses the dashboard.
5. The staff member selects a management module.
6. The application sends requests to the relevant REST controller.
7. The backend validates the request.
8. Spring JDBC performs the required database operation.
9. The application displays the result.
10. The staff member logs out after completing the work.

---

## 8. Acceptance Criteria

The system is considered acceptable when:

1. A new staff account can be registered successfully.
2. Duplicate usernames and email addresses are rejected.
3. Different staff accounts can log in using their own credentials.
4. Invalid login credentials are rejected.
5. Logout redirects the user to the login page.
6. Unauthenticated users cannot access protected dashboard resources.
7. Farmer records can be added, viewed, updated and deleted.
8. Field records can be added, viewed, updated and deleted.
9. Crop records can be added, viewed, updated and deleted.
10. Season records can be added, viewed, updated and deleted.
11. Yield records can be added, viewed, updated and deleted.
12. Input validation and database relationship constraints work correctly.
13. The application can connect to the configured PostgreSQL database.

---

## 9. Future Enhancements

The following features may be considered for future versions:

- Role-based permissions for different staff categories.
- Advanced yield prediction.
- Weather information integration.
- Crop recommendation features.
- Detailed report generation and export.
- Mobile-friendly enhancements.
- Audit logs for staff activities.

These are proposed enhancements and are not claimed as completed features in the current implementation.

---

## 10. Conclusion

The Agriculture Crop Management and Yield Analysis System provides a centralized platform for maintaining agricultural information.

By combining individual staff authentication, five CRUD management modules, Spring Boot REST APIs and PostgreSQL storage, the application simplifies the management of farmers, fields, crops, seasons and yield records.

This SRS document describes the requirements and expected behaviour of the implemented system and serves as a reference for development, testing, maintenance and future enhancement.