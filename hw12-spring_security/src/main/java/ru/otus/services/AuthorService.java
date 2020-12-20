package ru.otus.services;

import ru.otus.entities.Author;
import ru.otus.exceptions.NotFoundException;
import ru.otus.exceptions.ValidateException;

import java.util.List;

/*
 * @created 12/12 - otus-spring
 * @author Ilya Rogatkin
 */
public interface AuthorService {
    void save(String authorName) throws ValidateException;
    void update(long id, String authorName) throws NotFoundException;
    void delete(long id) throws NotFoundException, ValidateException;
    List<Author> findAll();
    Author findById(long id) throws ValidateException;
}
