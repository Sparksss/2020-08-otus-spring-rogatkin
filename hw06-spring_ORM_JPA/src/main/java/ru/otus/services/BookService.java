package ru.otus.services;

import ru.otus.models.Book;

import java.util.List;

/*
 * @created 14/11 - otus-spring
 * @author Ilya Rogatkin
 */
public interface BookService {
    void addBook(String bookName, String genreName) throws Exception;
    void update(long bookId, String bookName) throws Exception;
    List<Book> findAll();
    Book findById(long id) throws Exception;
    Long getCountBooks();
    void addAuthorToBook(long authorId, long bookId) throws Exception;
}
