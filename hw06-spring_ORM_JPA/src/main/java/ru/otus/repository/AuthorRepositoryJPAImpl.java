package ru.otus.repository;

import org.springframework.stereotype.Component;
import ru.otus.models.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/*
 * @created 14/11 - otus-spring
 * @author Ilya Rogatkin
 */
@Component
public class AuthorRepositoryJPAImpl implements AuthorRepositoryJPA {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int countAll() {
       return entityManager.createQuery("select count(a) from Author a", int.class).getSingleResult();
    }

    @Override
    public Author add(Author author) {
        if(author.getId() == 0) {
            entityManager.persist(author);
            return author;
        } else {
            return entityManager.merge(author);
        }
    }


    @Override
    public void delete(long id) {
        Query query = entityManager.createQuery("delete from Author a where a.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Author findById(long id) {
        return entityManager.find(Author.class, id);
    }

    @Override
    public Author findByName(String name) {
        TypedQuery<Author> query = entityManager.createQuery("select a.id, a.name from Author a where a.name like :name", Author.class);
        query.setParameter("name", String.format("%s%s%s", "%", name, "%"));
        return query.getSingleResult();
    }

    @Override
    public List<Author> getAll() {
        return entityManager.createQuery("select count(a) from Author a", Author.class).getResultList();
    }

    @Override
    public List<Author> getAllAuthorsByBookId(long bookId) {
        TypedQuery<Author> query = entityManager.createQuery("select a.id, a.name from Author a join a.books_authors ba where ba.book_id = :book_id", Author.class);
        query.setParameter("book_id", bookId);
        return query.getResultList();
    }
}
