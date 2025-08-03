-- liquibase formatted sql

-- changeset acycalov:1
CREATE TABLE user_model(
    id SERIAL PRIMARY KEY,
    email TEXT,
    first_name TEXT,
    last_name TEXT,
    phone TEXT,
    image TEXT,
    role VARCHAR);

    -- changeset acycalov:2
    CREATE TABLE ad_model(
        pk SERIAL PRIMARY KEY,
        author_id SERIAL REFERENCES user_model(id) ON DELETE CASCADE,
        image_id TEXT,
        title TEXT,
        price INTEGER,
        description TEXT);

-- changeset acycalov:3
    CREATE TABLE comment_model(
        pk SERIAL PRIMARY KEY,
        author_id SERIAL REFERENCES user_model(id) ON DELETE CASCADE,
        create_at INTEGER,
        text TEXT);

-- changeset acycalov:4
    CREATE TABLE avatar_user(
        id SERIAL PRIMARY KEY,
        user_id SERIAL REFERENCES user_model(id) ON DELETE CASCADE,
        file_patch TEXT,
        media_type TEXT,
        file_size INTEGER,
        data BYTEA );

-- changeset acycalov:5
    CREATE TABLE avatar_ad(
        id SERIAL PRIMARY KEY,
        ad_id SERIAL REFERENCES ad_model(pk) ON DELETE CASCADE,
        file_patch TEXT,
        media_type TEXT,
        file_size INTEGER,
        data BYTEA);

