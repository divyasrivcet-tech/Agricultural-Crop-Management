# Agriculture Crop Management and Yield Analysis System

A web-based academic project for maintaining farmer, field, crop, season and yield records. The application uses **Java 21, Spring Boot, PostgreSQL, HTML, CSS and JavaScript**. A separate Python script analyses an exported yield-report CSV using Pandas and Matplotlib.

> **Scope note:** This repository implements CRUD management and descriptive yield analysis. Weather integration, automated irrigation/fertilizer recommendations, authentication and machine-learning prediction are not included in the current implementation.

## Project modules

| Module | Main operations | Main data |
|---|---|---|
| Farmer Management | Add, view, update, delete | Name, phone, village, district |
| Field Management | Add, view, update, delete | Farmer, field name, area in acres |
| Crop Management | Add, view, update, delete | Crop name, crop type |
| Season Management | Add, view, update, delete | Season name, start date, end date |
| Yield Record Management | Add, view, update, delete | Field, crop, season, yield in kg |

Yield records link a field, crop and season. The application serves its static frontend from Spring Boot at `http://localhost:8080`.

## Technology stack

- Java 21
- Spring Boot 3.5.6
- Maven
- Spring JDBC (`JdbcTemplate`)
- PostgreSQL
- HTML5, CSS3, JavaScript (Fetch API)
- Python 3, Pandas, Matplotlib

## Repository layout

```text
Agriculture_Crop_Management/
├── database/                 # PostgreSQL schema, sample data, useful queries
├── docs/                     # Course mapping and technical documentation
├── Documentation/
│   ├── Project_Report/       # Main report (PDF and editable DOCX)
│   ├── Subject_Reports/      # DBMS, Java, SE and FDS reports
│   ├── Diagrams/             # Mermaid diagrams rendered by GitHub
│   ├── Course_Documents/     # Supplied course documents
│   └── Evidence/             # Images extracted from supplied documents
├── python/                   # Yield analysis script and CSV template
├── scripts/                  # PowerShell API smoke-test helper
├── src/main/java/            # Spring Boot application and REST controllers
├── src/main/resources/static # Web interface
├── pom.xml
└── Dockerfile
```

## Requirements

- JDK 21
- Maven 3.9+
- PostgreSQL 14+ (or a compatible supported PostgreSQL version)
- Python 3.9+ only if running the optional analysis

## Setup and run

### 1. Create the database

Create a PostgreSQL database named `agriculture_db`.

In pgAdmin Query Tool, select that database and run:

```text
database/01_create_tables.sql
```

For a new/empty database only, optional sample records can be added using:

```text
database/02_sample_data_for_empty_tables.sql
```

Do not rerun sample inserts without reviewing the existing rows.

If an older copy of the project database uses `record_id` instead of `yield_id` in `yield_records`, review and run `database/04_align_existing_schema.sql` before starting the updated application. Back up the database first.

### 2. Configure the database connection

The tracked `application.properties` is intentionally excluded from Git because it contains local configuration. Create it from the example:

**PowerShell**
```powershell
Copy-Item src/main/resources/application.properties.example src/main/resources/application.properties
$env:DB_URL = "jdbc:postgresql://localhost:5432/agriculture_db"
$env:DB_USERNAME = "postgres"
$env:DB_PASSWORD = "your_local_postgres_password"
```

Do not commit `application.properties` or share your database password.

### 3. Start the application

From the project root:

```powershell
mvn spring-boot:run
```

Open:

```text
http://localhost:8080
```

Keep the terminal open while using the application.

### 4. Optional Python analysis

Install dependencies:

```powershell
python -m pip install -r python/requirements.txt
```

In pgAdmin, run query 4 from `database/03_useful_queries.sql` and export the result as:

```text
python/yield_report.csv
```

Then run:

```powershell
python python/yield_analysis.py
```

The script prints basic descriptive statistics and, when `crop_name` is present, saves a crop-wise bar chart as `python/yield_by_crop.png`. The CSV is based on your database export; the repository includes a header-only template, not fabricated live records.

## REST API summary

All endpoints use JSON unless noted.

| Resource | Collection endpoint | Item endpoint |
|---|---|---|
| Health | `GET /api/health` | — |
| Farmers | `/api/farmers` | `/api/farmers/{id}` |
| Fields | `/api/fields` | `/api/fields/{id}` |
| Crops | `/api/crops` | `/api/crops/{id}` |
| Seasons | `/api/seasons` | `/api/seasons/{id}` |
| Yield records | `/api/yield-records` | `/api/yield-records/{id}` |

For each resource, collection `GET` lists records, `POST` creates a record, item `GET` retrieves one record, item `PUT` updates it, and item `DELETE` deletes it. See [API documentation](Documentation/API_Documentation.md) for request examples.

## Documentation

- [Main Project Report (PDF)](Documentation/Project_Report/Main_Project_Report.pdf)
- [Main Project Report (Word)](Documentation/Project_Report/Main_Project_Report.docx)
- [Subject Reports](Documentation/Subject_Reports/README.md)
- [API Documentation](Documentation/API_Documentation.md)
- [Installation Guide](Documentation/Installation_Guide.md)
- [Deployment Guide](Documentation/Deployment_Guide.md)
- [Testing Plan and Evidence Status](Documentation/Testing_Report.md)
- [System Diagrams](Documentation/Diagrams/README.md)
- [Course Mapping](docs/course-mapping.md)

## Testing

A PowerShell smoke-test script is provided at `scripts/test-api.ps1`. It requires the application and database to be running. It creates temporary records and attempts cleanup. Review the script before running it against any non-test database.

No test execution result is claimed by this documentation. Record actual test outcomes and attach genuine screenshots after running the application in your environment.

## Security and limitations

- Never commit passwords, `.env` files or local `application.properties`.
- Use a dedicated database user with only the permissions required by the application.
- The current project does not include login/role-based authorization. Do not expose an unauthenticated instance to the public internet with real personal data.
- Back up PostgreSQL before schema changes.
- The Python script performs descriptive analysis; it is not a crop-yield prediction model.

## Maintainer checklist

Before submission, replace report placeholders (student name, register number, institution, department, guide and academic year), run the application, execute the smoke tests, and add actual screenshots to `Documentation/Evidence/Project_Run/`.
