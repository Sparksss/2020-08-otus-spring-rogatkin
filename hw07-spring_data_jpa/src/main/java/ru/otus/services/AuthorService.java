package ru.otus.services;

import ru.otus.entities.Author;

import java.util.List;

/*
 * @created 07/12 - otus-spring
 * @author Ilya Rogatkin
 */
public interface AuthorService {
    void save(String authorName) throws Exception;
    void update(long id, String authorName) throws Exception;
    void delete(long id) throws Exception;
    List<Author> findAll();
    Author findById(long id) throws Exception;
    Author findByName(String name) throws Exception;
}
