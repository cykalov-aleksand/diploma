-- liquibase formatted sql

-- changeset acycalov:1
CREATE TABLE usermodel(
    id SERIAL PRIMARY KEY,
    email TEXT,
    firstName TEXT,
    lastName TEXT,
    phone TEXT,
    image TEXT,
    role TEXT);
