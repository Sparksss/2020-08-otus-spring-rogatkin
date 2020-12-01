insert into public.genres (name) values ('sci_fiction'), ('Detective'), ('Novel');
insert into public.authors(name) values ('Alexander_Pushkin'), ('Leo_Tolstoy');
insert into public.books (name, genre_id) values ('The Captains Daughter', 3);
insert into public.books_authors (book_id, author_id) values (1, 1);
insert into public.comments(book_id, comment) VALUES (1, 'Отличная книга, класска!');