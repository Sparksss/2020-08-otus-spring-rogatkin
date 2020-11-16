package ru.otus.repository;

import ru.otus.models.BookAuthor;

/*
 * @created 16/11 - otus-spring
 * @author Ilya Rogatkin
 */
public interface BookAuthorRepositoryJPA {
    BookAuthor save(BookAuthor bookAuthor);
}
