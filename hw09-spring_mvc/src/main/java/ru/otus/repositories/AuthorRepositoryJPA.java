package ru.otus.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.entities.Author;

import java.util.List;

/*
 * @created 05/12 - otus-spring
 * @author Ilya Rogatkin
 */
public interface AuthorRepositoryJPA extends CrudRepository<Author, Long> {
    long count();
    Author save(Author author);
    void delete(Author author);
    Author findById(long id);
    Author findByName(String name);
    List<Author> findAll();
}