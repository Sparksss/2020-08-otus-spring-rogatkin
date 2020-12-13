CREATE TABLE users (
    id bigserial primary key ,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL
);


CREATE TABLE genres (
    id bigserial primary key,
    name varchar(200)
);

CREATE TABLE authors (
    id bigserial primary key,
    name varchar(200)
);

CREATE TABLE books (
    id bigserial primary key,
    name varchar(200) not null,
    genre_id bigserial references genres(id)
);

CREATE TABLE books_authors (
    id bigserial primary key,
    book_id bigserial references books(id) ON DELETE CASCADE,
    author_id bigserial references authors(id) ON DELETE CASCADE
);

CREATE TABLE comments (
    id bigserial primary key,
    book_id bigserial references books(id),
    comment text
);
