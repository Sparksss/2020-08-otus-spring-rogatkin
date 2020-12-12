-- DROP TABLE if exists genres cascade;
-- DROP TABLE if exists authors cascade;
-- drop table if exists books cascade;
-- drop table if exists books_authors cascade;

CREATE TABLE genres (
    id int8 auto_increment,
    name varchar(200)
);

CREATE TABLE authors (
    id int8 auto_increment,
    name varchar(200)
);

CREATE TABLE books (
    id int8 auto_increment,
    name varchar(200) not null,
    genre_id int8 references genres(id)
);

CREATE TABLE books_authors (
    id int8 auto_increment,
    book_id int8 references books(id) ON DELETE CASCADE,
    author_id int8 references authors(id) ON DELETE CASCADE
);

CREATE TABLE comments (
    id int8 auto_increment,
    book_id int8 references books(id),
    comment text
);