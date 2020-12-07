package ru.otus.services;

import ru.otus.entities.Book;

import java.util.List;

/*
 * @created 07/12 - otus-spring
 * @author Ilya Rogatkin
 */
public interface BookService {
    void addBook(String bookName, String genreName) throws Exception;
    void update(long bookId, String bookName) throws Exception;
    List<Book> findAll();
    Book findById(long id) throws Exception;
    Long getCountBooks();
    void addAuthorToBook(long authorId, long bookId) throws Exception;
    void addCommentToBook(String commentText, long bookId) throws Exception;
}
