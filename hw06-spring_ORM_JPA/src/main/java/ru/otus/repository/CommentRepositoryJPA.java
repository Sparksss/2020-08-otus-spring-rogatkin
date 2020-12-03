package ru.otus.repository;

import ru.otus.models.Comment;

import java.util.List;

/*
 * @created 25/11 - otus-spring
 * @author Ilya Rogatkin
 */
public interface CommentRepositoryJPA {
    Comment save(Comment comment);
    void delete(Comment comment);
    Comment findById(long id);
}
