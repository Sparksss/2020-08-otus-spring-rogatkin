package ru.otus.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.entities.Comment;

/*
 * @created 05/12 - otus-spring
 * @author Ilya Rogatkin
 */
public interface CommentRepositoryJPA extends CrudRepository<Comment, Long> {}