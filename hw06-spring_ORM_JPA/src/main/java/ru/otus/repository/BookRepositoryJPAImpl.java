package ru.otus.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.otus.models.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class BookRepositoryJPAImpl implements BookRepositoryJPA {

    @PersistenceContext
    private EntityManager em;

    private BookAuthorRepositoryJPA bookAuthorRepositoryJPA;

    @Autowired
    public BookRepositoryJPAImpl(EntityManager em, BookAuthorRepositoryJPA bookAuthorRepositoryJPA) {
        this.em = em;
        this.bookAuthorRepositoryJPA = bookAuthorRepositoryJPA;
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
        return em.createQuery("select b from Book b", Book.class).getResultList();
    }

    @Override
    public void updateNameById(long id, String name) {
        Query query = em.createQuery("update Book b set b.name = :name where b.id = :id");
        query.setParameter("id", id);
        query.setParameter("name", name);
        query.executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Book where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public List<Book> findAllByAuthor(long authorId) {
        TypedQuery<Book> query = em.createQuery("select b from Book b inner join BookAuthor ba on b.id = ba.bookId inner join Author a on ba.authorId = a.id where a.id = :authorId", Book.class);
        query.setParameter("authorId", authorId);
        return query.getResultList();
    }

    @Override
    public List<Book> findAllByGenre(long genreId) {
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.genre.id = :genreId", Book.class);
        query.setParameter("genreId", genreId);
        return query.getResultList();
    }

    @Override
    public int countBook() {
        return this.em.createQuery("select count(b) from Book b", int.class).getSingleResult();
    }
}
