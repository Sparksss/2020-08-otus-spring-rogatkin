package ru.otus.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.otus.models.BookAuthor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/*
 * @created 16/11 - otus-spring
 * @author Ilya Rogatkin
 */
@Repository
public class BookAuthorRepositoryJPAImpl implements BookAuthorRepositoryJPA {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public BookAuthorRepositoryJPAImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public BookAuthor save(BookAuthor bookAuthor) {
        if(bookAuthor.getId() == 0) {
            entityManager.persist(bookAuthor);
            return bookAuthor;
        } else {
          return entityManager.merge(bookAuthor);
        }
    }
}
