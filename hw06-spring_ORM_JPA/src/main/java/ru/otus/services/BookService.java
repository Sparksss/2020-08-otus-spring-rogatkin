package ru.otus.services;

import ru.otus.models.Author;
import ru.otus.models.Book;

import java.util.List;

/*
 * @created 14/11 - otus-spring
 * @author Ilya Rogatkin
 */
public interface BookService {
    void addBook(String bookName, String genreName) throws Exception;
    void addAuthorToBook(Book book, Author author) throws Exception;
    void update(long bookId, String bookName) throws Exception;
    List<Book> findAll();
    Book findById(long id) throws Exception;
    int getCountBooks();
    List<Book> getBooksByAuthor(Author author) throws Exception;
    List<Book> getBooksByGenre(String genreName) throws Exception;
}
