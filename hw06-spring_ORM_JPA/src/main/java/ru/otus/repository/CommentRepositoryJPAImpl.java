package ru.otus.repository;

import org.springframework.stereotype.Repository;
import ru.otus.models.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/*
 * @created 25/11 - otus-spring
 * @author Ilya Rogatkin
 */
@Repository
public class CommentRepositoryJPAImpl implements CommentRepositoryJPA {

    @PersistenceContext
    private EntityManager entityManager;

    public CommentRepositoryJPAImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Comment findById(long id) {
        return entityManager.find(Comment.class, id);
    }

    @Override
    public Comment save(Comment comment) {
        if(comment.getId() == 0) {
            this.entityManager.persist(comment);
            return comment;
        } else {
            return this.entityManager.merge(comment);
        }
    }

    @Override
    public void delete(Comment comment) {
        this.entityManager.remove(comment);
    }
}
