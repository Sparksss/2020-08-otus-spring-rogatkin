package ru.otus.services;

import org.springframework.stereotype.Component;
import ru.otus.dao.BookDaoJdbc;
import ru.otus.domains.Book;

import java.util.List;

/**
 * Created by ilya on Oct, 2020
 */
@Component
public class BookService {
    BookDaoJdbc bookDaoJdbc;

    public BookService(BookDaoJdbc bookDaoJdbc) {
        this.bookDaoJdbc = bookDaoJdbc;
    }

   public void save(Book book) {
        this.bookDaoJdbc.insert(book);
    }

    public List<Book> findAll() {
        return this.bookDaoJdbc.getAll();
    }

    public Book findById(long id) { return this.bookDaoJdbc.getById(id); }
}
