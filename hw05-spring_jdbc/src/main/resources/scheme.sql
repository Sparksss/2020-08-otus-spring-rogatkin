CREATE TABLE genres (
    id serial primary key,
    name varchar(200)
);

CREATE TABLE authors (
    id serial primary key,
    name varchar(200)
);

CREATE TABLE books (
    id serial primary key,
    name varchar(200) not null,
    genre_id int references genres(id)
);

CREATE TABLE books_authors (
    id serial primary key,
    book_id int references books(id),
    author_id int references authors(id)
);