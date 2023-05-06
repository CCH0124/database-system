CREATE USER cch WITH ENCRYPTED PASSWORD '123456' CREATEDB;
--ALTER USER itachi WITH CREATEDB;--
CREATE DATABASE account WITH
        OWNER = cch
        ENCODING = 'UTF8'
        TABLESPACE = pg_default
        CONNECTION LIMIT = -1;
GRANT ALL PRIVILEGES ON DATABASE account TO cch;

\c account
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE accounts  (
        user_id uuid DEFAULT uuid_generate_v4 () PRIMARY KEY,
	username VARCHAR ( 50 ) UNIQUE NOT NULL,
	password VARCHAR ( 50 ) NOT NULL,
	email VARCHAR ( 255 ) UNIQUE NOT NULL,
	created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
        last_login TIMESTAMP 
);
ALTER TABLE accounts OWNER TO cch;

INSERT INTO accounts (username, password, email) VALUES 
(
        'itachi', '123456', 'itachi@example.com'
),
(
        'naruto', '1234567', 'naruto@example.com'
);