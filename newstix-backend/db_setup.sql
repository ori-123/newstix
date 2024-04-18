-- Load environment variable from .env file
\set DB_NAME `cat .env | grep ^DB_NAME= | cut -d '=' -f2-`

-- Create database if it doesn't exist and connect to it
\c postgres

DO $$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = :'DB_NAME') THEN
            CREATE DATABASE :'DB_NAME';
        END IF;
    END $$;

\c :'DB_NAME'

-- Create users table if it doesn't exist
CREATE TABLE IF NOT EXISTS users (
                                     id SERIAL PRIMARY KEY,
                                     username VARCHAR(255) UNIQUE NOT NULL,
                                     password VARCHAR(255) NOT NULL,
                                     language VARCHAR(20) NOT NULL,
                                     country VARCHAR(50) NOT NULL,
                                     category VARCHAR(50) NOT NULL,
                                     timeframe VARCHAR(50)
);

-- Display status message
SELECT 'Database and table created successfully' AS status;
