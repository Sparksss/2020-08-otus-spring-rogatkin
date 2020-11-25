package ru.otus.repository;

import ru.otus.models.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/*
 * @created 25/11 - otus-spring
 * @author Ilya Rogatkin
 */
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
    public void delete(long id) {
        Query query = this.entityManager.createQuery("delete from Comment c where c.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
