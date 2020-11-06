-- add Genres
insert into genres (name)
values ('Romance');
insert into genres (name)
values ('Comedy');
insert into authors(name)

-- add authors
values ('Anton Chekhov');
insert into authors (name)
values ('Alexander Pushkin');

-- add books
insert into books (name, genre_id)
values ('My Live Story', 2);
insert into books_authors (book_id, author_id)
values (1, 1);
insert into books(name)
values ('My first book');

insert into books_authors(book_id, author_id) values (1,1);
