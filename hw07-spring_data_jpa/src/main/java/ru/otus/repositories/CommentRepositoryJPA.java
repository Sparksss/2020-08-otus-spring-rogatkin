package ru.otus.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.entities.Comment;

/*
 * @created 07/12 - otus-spring
 * @author Ilya Rogatkin
 */
public interface CommentRepositoryJPA extends CrudRepository<Comment, Long> {
    Comment save(Comment comment);
    void delete(Comment comment);
    Comment findById(long id);
}