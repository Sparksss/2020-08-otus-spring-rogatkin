package ru.otus.repository;

import org.springframework.stereotype.Repository;
import ru.otus.models.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class BookRepositoryJPAImpl implements BookRepositoryJPA {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public Book save(Book book) {
        if(book.getId() == 0) {
            em.persist(book);
            return book;
        } else {
          return em.merge(book);
        }
    }

    @Override
    public Book findById(long id) {
        return em.find(Book.class, id);
    }

    @Override
    public Book findByName(String name) {
        TypedQuery<Book> query = em.createQuery("select b.id, b.name from Book b where b.name like :name", Book.class);
        query.setParameter("name", String.format("%s%s%s", "%", name, "%"));
        return query.getSingleResult();
    }

    @Override
    public List<Book> findAll() {
        return em.createQuery("select b.id, b.name from Book b", Book.class).getResultList();
    }

    @Transactional
    @Override
    public void updateNameById(long id, String name) {
        Query query = em.createQuery("update Book b set b.name = :name where b.id = :id");
        query.setParameter("id", id);
        query.setParameter("name", name);
        query.executeUpdate();
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Book where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}