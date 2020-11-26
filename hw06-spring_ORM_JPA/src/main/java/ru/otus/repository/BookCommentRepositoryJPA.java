package ru.otus.repository;

import ru.otus.models.BookComment;

/*
 * @created 26/11 - otus-spring
 * @author Ilya Rogatkin
 */
public interface BookCommentRepositoryJPA {
    void save(BookComment bookComment);
}
