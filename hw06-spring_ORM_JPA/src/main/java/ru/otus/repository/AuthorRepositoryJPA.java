package ru.otus.repository;

import ru.otus.models.Author;

import java.util.List;

/*
 * @created 14/11 - otus-spring
 * @author Ilya Rogatkin
 */
public interface AuthorRepositoryJPA {
    Long countAll();
    Author save(Author author);
    void delete(Author author);
    Author findById(long id);
    Author findByName(String name);
    List<Author> getAll();
}
