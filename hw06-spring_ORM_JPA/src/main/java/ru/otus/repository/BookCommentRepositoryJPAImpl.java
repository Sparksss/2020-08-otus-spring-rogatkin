package ru.otus.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.otus.models.BookComment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/*
 * @created 26/11 - otus-spring
 * @author Ilya Rogatkin
 */
@Repository
public class BookCommentRepositoryJPAImpl implements BookCommentRepositoryJPA {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public BookCommentRepositoryJPAImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(BookComment bookComment) {
        this.entityManager.persist(bookComment);
    }
}
