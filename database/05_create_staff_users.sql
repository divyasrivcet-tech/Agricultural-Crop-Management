CREATE TABLE IF NOT EXISTS staff_users (
    staff_id SERIAL PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(150) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE UNIQUE INDEX IF NOT EXISTS idx_staff_username_lower
ON staff_users (LOWER(username));

CREATE UNIQUE INDEX IF NOT EXISTS idx_staff_email_lower
ON staff_users (LOWER(email));
