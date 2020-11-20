package ru.otus.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.otus.models.BookAuthor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/*
 * @created 16/11 - otus-spring
 * @author Ilya Rogatkin
 */
@Repository
public class BookAuthorRepositoryRepositoryJPAImpl implements BookAuthorRepositoryJPA {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public BookAuthorRepositoryRepositoryJPAImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
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
