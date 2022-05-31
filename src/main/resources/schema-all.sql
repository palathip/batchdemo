DROP TABLE people IF EXISTS;

CREATE TABLE people  (
    person_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    application_no VARCHAR(20),
    first_name VARCHAR(20),
    last_name VARCHAR(20)
);