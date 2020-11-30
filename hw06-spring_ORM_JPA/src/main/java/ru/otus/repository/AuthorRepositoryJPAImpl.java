package ru.otus.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.otus.models.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/*
 * @created 14/11 - otus-spring
 * @author Ilya Rogatkin
 */
@Repository
public class AuthorRepositoryJPAImpl implements AuthorRepositoryJPA {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public AuthorRepositoryJPAImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public Long countAll() {
       return entityManager.createQuery("select count(a) from Author a", Long.class).getSingleResult();
    }

    @Override
    public Author save(Author author) {
        if(author.getId() == 0) {
            entityManager.persist(author);
            return author;
        } else {
            return entityManager.merge(author);
        }
    }


    @Override
    public void delete(Author author) {
        this.entityManager.remove(author);
    }

    @Override
    public Author findById(long id) {
        return entityManager.find(Author.class, id);
    }

    @Override
    public Author findByName(String name) {
        TypedQuery<Author> query = entityManager.createQuery("select a from Author a where a.name like :name", Author.class);
        query.setParameter("name", String.format("%s%s%s", "%", name, "%"));
        return query.getSingleResult();
    }

    @Override
    public List<Author> getAll() {
        return entityManager.createQuery("select a from Author a", Author.class).getResultList();
    }
}
