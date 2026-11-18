# System Diagrams

GitHub renders Mermaid diagrams embedded in Markdown. The diagrams below describe the current implementation, not proposed future features.

## 1. System architecture

```mermaid
flowchart TD
    U[User / Browser] --> UI[HTML, CSS, JavaScript]
    UI --> API[Spring Boot REST Controllers]
    API --> JDBC[Spring JDBC Template]
    JDBC --> DB[(PostgreSQL agriculture_db)]
    DB --> SQL[Relational Tables]
    DB --> EXP[Yield Report Query Export]
    EXP --> CSV[python/yield_report.csv]
    CSV --> PY[Python + Pandas]
    PY --> MAT[Matplotlib Chart]
```

## 2. Entity relationship overview

```mermaid
erDiagram
    FARMERS ||--o{ FIELDS : owns
    FIELDS ||--o{ YIELD_RECORDS : has
    CROPS ||--o{ YIELD_RECORDS : recorded_for
    SEASONS ||--o{ YIELD_RECORDS : occurs_in

    FARMERS {
      int farmer_id PK
      string farmer_name
      string phone
      string village
      string district
    }
    FIELDS {
      int field_id PK
      int farmer_id FK
      string field_name
      decimal area_acres
    }
    CROPS {
      int crop_id PK
      string crop_name
      string crop_type
    }
    SEASONS {
      int season_id PK
      string season_name
      date start_date
      date end_date
    }
    YIELD_RECORDS {
      int record_id PK
      int field_id FK
      int crop_id FK
      int season_id FK
      decimal yield_kg
    }
```

## 3. Use-case overview

```mermaid
flowchart LR
    A([Farmer / Project Operator])
    subgraph S[Agriculture Crop Management System]
      F[Manage Farmers]
      L[Manage Fields]
      C[Manage Crops]
      SE[Manage Seasons]
      Y[Manage Yield Records]
      H[Check Database Connection]
      R[Review Yield Analysis]
    end
    A --> F
    A --> L
    A --> C
    A --> SE
    A --> Y
    A --> H
    A --> R
```

## 4. Yield-record workflow

```mermaid
flowchart TD
    A([Start]) --> B[Select existing field]
    B --> C[Select crop]
    C --> D[Select season]
    D --> E[Enter yield in kg]
    E --> F{Inputs valid?}
    F -- No --> G[Display validation error]
    G --> E
    F -- Yes --> H[POST /api/yield-records]
    H --> I[Insert into PostgreSQL]
    I --> J[Refresh yield table]
    J --> K([End])
```

## 5. Application module sequence

```mermaid
flowchart TD
    A[Start application] --> B[Configure PostgreSQL]
    B --> C[Launch Spring Boot]
    C --> D[Open browser]
    D --> E[Manage farmer]
    E --> F[Manage field]
    F --> G[Manage crop]
    G --> H[Manage season]
    H --> I[Record yield]
    I --> J[Export yield report]
    J --> K[Run Python analysis]
```

---

## 6. Staff Authentication Use Case Diagram

```mermaid
flowchart TD
    STAFF([Staff User])
    REG[Register Account]
    LOGIN[Login]
    LOGOUT[Logout]
    DASH[Access Dashboard]
    FARM[Manage Farmers]
    FIELD[Manage Fields]
    CROP[Manage Crops]
    SEASON[Manage Seasons]
    YIELD[Manage Yield Records]

    STAFF --> REG
    STAFF --> LOGIN
    LOGIN --> DASH
    DASH --> FARM
    DASH --> FIELD
    DASH --> CROP
    DASH --> SEASON
    DASH --> YIELD
    DASH --> LOGOUT
```

---

## 7. Staff Registration and Login Sequence Diagram


```mermaid
sequenceDiagram
    actor Staff
    participant Browser
    participant SpringBoot
    participant Database

    Staff->>Browser: Open registration page
    Staff->>Browser: Enter registration details
    Browser->>SpringBoot: POST /api/auth/register
    SpringBoot->>SpringBoot: Validate details and hash password
    SpringBoot->>Database: Insert staff account
    Database-->>SpringBoot: Registration result
    SpringBoot-->>Browser: Registration success

    Staff->>Browser: Enter login credentials
    Browser->>SpringBoot: POST /api/auth/login
    SpringBoot->>Database: Find staff username
    Database-->>SpringBoot: User and password hash
    SpringBoot->>SpringBoot: Verify BCrypt password
    SpringBoot-->>Browser: Login success and dashboard


```
