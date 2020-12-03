package ru.otus.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.otus.models.Book;

import javax.persistence.*;
import java.util.List;

@Repository
public class BookRepositoryJPAImpl implements BookRepositoryJPA {

    @PersistenceContext
    private final EntityManager em;

    @Autowired
    public BookRepositoryJPAImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Book save(Book book) {
        if(book.getId() == 0) {
            em.persist(book);
        } else {
          book = em.merge(book);
        }
        return book;
    }

    @Override
    public Book findById(long id) {
        return em.find(Book.class, id);
    }

    @Override
    public Book findByName(String name) {
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.name like :name", Book.class);
        query.setParameter("name", String.format("%s%s%s", "%", name, "%"));
        return query.getSingleResult();
    }

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = this.em.createQuery("select b from Book b join fetch b.genre left join fetch b.comments", Book.class);
        return query.getResultList();
    }

    @Override
    public void delete(Book book) {
        this.em.remove(book);
    }

    @Override
    public Long countBook() {
        return this.em.createQuery("select count(b) from Book b", Long.class).getSingleResult();
    }
}
