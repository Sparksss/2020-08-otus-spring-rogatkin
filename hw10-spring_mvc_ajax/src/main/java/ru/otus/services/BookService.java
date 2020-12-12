package ru.otus.services;

import ru.otus.entities.Book;
import ru.otus.exceptions.NotFoundException;
import ru.otus.exceptions.ValidateException;

import java.util.List;

/*
 * @created 14/11 - otus-spring
 * @author Ilya Rogatkin
 */
public interface BookService {
    Book addBook(Book book) throws ValidateException, NotFoundException;
    Book update(Book book) throws ValidateException, NotFoundException;
    List<Book> findAll();
    Book findById(long id) throws ValidateException, NotFoundException;
    Long getCountBooks();
    void addAuthorToBook(long authorId, long bookId) throws ValidateException, NotFoundException;
    void delete(long bookId) throws ValidateException, NotFoundException;
}
