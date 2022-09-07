CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username varchar UNIQUE,
    password TEXT
);

CREATE TABLE authorities (
    id SERIAL PRIMARY KEY,
    authority TEXT
);

CREATE TABLE users_authorities (
    user_id INT REFERENCES users(id),
    authority_id INT REFERENCES authorities(id)
);

create table posts (
    id serial primary key,
    name varchar(2000),
    description text,
    created timestamp without time zone not null default now(),
    user_id INT NOT NULL REFERENCES users(id)
);

CREATE TABLE messages (
    id SERIAL PRIMARY KEY,
    text TEXT,
    post_id INT REFERENCES posts(id) ON DELETE CASCADE,
    user_id INT NOT NULL REFERENCES users(id),
    created timestamp without time zone not null default now()
);

