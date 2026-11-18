# Installation and User Guide

## Software required

1. JDK 21
2. Apache Maven 3.9 or later
3. PostgreSQL and pgAdmin
4. A modern browser
5. Python 3.9+ for the optional yield analysis

## Database preparation

1. Start PostgreSQL.
2. Create a database named `agriculture_db`.
3. Open pgAdmin Query Tool for that database.
4. Run `database/01_create_tables.sql`.
5. Optionally run `database/02_sample_data_for_empty_tables.sql` on a new/empty project database only.
6. If upgrading a database created with the earlier `record_id` yield key, back it up and review `database/04_align_existing_schema.sql`.

## Application configuration

Copy `src/main/resources/application.properties.example` to `src/main/resources/application.properties`.

Set environment variables in PowerShell (replace the password locally):

```powershell
$env:DB_URL = "jdbc:postgresql://localhost:5432/agriculture_db"
$env:DB_USERNAME = "postgres"
$env:DB_PASSWORD = "your_local_password"
```

The local `application.properties` file is ignored by Git. Do not upload it.

## Run

```powershell
mvn spring-boot:run
```

Browse to `http://localhost:8080`.

## Use the modules

1. Add a farmer first.
2. Add a field and select its farmer.
3. Add crops.
4. Add seasons with valid start and end dates.
5. Add yield records by selecting an existing field, crop and season.
6. Use Edit and Delete actions in the records table as needed.
7. Use the database connection check to verify the backend can query PostgreSQL.

Foreign-key relationships mean linked parent records cannot be deleted while child records still refer to them.

## Python yield analysis

1. Run query 4 from `database/03_useful_queries.sql`.
2. Export the result to `python/yield_report.csv`.
3. Install packages with `python -m pip install -r python/requirements.txt`.
4. Run `python python/yield_analysis.py`.
5. Review the printed summary and generated `python/yield_by_crop.png`.

## Common problems

- **Connection refused:** confirm PostgreSQL is running and `DB_URL` has the correct port/database.
- **Password authentication failed:** check `DB_USERNAME` and `DB_PASSWORD`.
- **Relation does not exist:** select `agriculture_db` before running the create-table script.
- **Yield column not found:** inspect the table schema and run the reviewed compatibility script if the database uses the old `record_id` name.
- **Port 8080 is busy:** stop the other process or set `PORT` to another port.
- **No records shown:** add parent records first, then create dependent records.
