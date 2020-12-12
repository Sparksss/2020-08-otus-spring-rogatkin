package ru.otus.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.entities.Author;

import java.util.List;

/*
 * @created 07/12 - otus-spring
 * @author Ilya Rogatkin
 */
public interface AuthorRepositoryJPA extends CrudRepository<Author, Long> {
    Author findByName(String name);
}
