package ru.otus.services;

import ru.otus.dto.BookDto;
import ru.otus.models.Book;

import java.util.List;

/*
 * @created 14/11 - otus-spring
 * @author Ilya Rogatkin
 */
public interface BookService {
    void addBook(String bookName, String genreName) throws Exception;
    void addAuthorToBook(long bookId, long authorId) throws Exception;
    void update(long bookId, String bookName) throws Exception;
    List<Book> findAll();
    BookDto findById(long id) throws Exception;
    Long getCountBooks();
    List<Book> getBooksByAuthor(long authorId) throws Exception;
    List<Book> getBooksByGenre(long genreId) throws Exception;
}
