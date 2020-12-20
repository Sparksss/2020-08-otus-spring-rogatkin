package ru.otus.services;

import ru.otus.entity.Book;
import ru.otus.exceptions.NotFoundException;
import ru.otus.exceptions.ValidationException;

import java.util.List;

/*
 * @created 16/12 - otus-spring
 * @author Ilya Rogatkin
 */
public interface BookService {
    Book add(Book book) throws ValidationException;
    void update(Book book) throws ValidationException, NotFoundException;
    void delete(Book book) throws ValidationException, NotFoundException;
    Book findById(String bookId) throws ValidationException, NotFoundException;
    Book findByName(String name) throws ValidationException, NotFoundException;
    List<Book> findAll();
}
