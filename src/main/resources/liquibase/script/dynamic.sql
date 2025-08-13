-- liquibase formatted sql

 -- changeset acycalov:1
            CREATE TABLE register_user_model(
            id SERIAL PRIMARY KEY,
                user_name TEXT,
                password TEXT,
                role VARCHAR,
                new_password TEXT);


-- changeset acycalov:2
CREATE TABLE user_model(
    ppk SERIAL PRIMARY KEY,
    first_name TEXT,
    last_name TEXT,
    phone TEXT,
    image TEXT,
    id_register SERIAL REFERENCES register_user_model(id)ON DELETE CASCADE);

    -- changeset acycalov:3
    CREATE TABLE ad_model(
        pk SERIAL PRIMARY KEY,
        author SERIAL REFERENCES user_model(ppk) ON DELETE CASCADE,
        image TEXT,
        title TEXT,
        price INTEGER,
        description TEXT);

-- changeset acycalov:4
    CREATE TABLE comment_model(
        pk SERIAL PRIMARY KEY,
        ad_pk SERIAL REFERENCES ad_model(pk) ON DELETE CASCADE,
        created_at BIGINT,
        text TEXT);

-- changeset acycalov:5
    CREATE TABLE avatar_user(
        id SERIAL PRIMARY KEY,
        user_id SERIAL REFERENCES user_model(ppk) ON DELETE CASCADE,
        file_patch TEXT,
        media_type TEXT,
        file_size INTEGER,
        data BYTEA );

-- changeset acycalov:6
    CREATE TABLE avatar_ad(
        id SERIAL PRIMARY KEY,
        ad_pk SERIAL REFERENCES ad_model(pk) ON DELETE CASCADE,
        file_patch TEXT,
        media_type TEXT,
        file_size INTEGER,
        data BYTEA);

