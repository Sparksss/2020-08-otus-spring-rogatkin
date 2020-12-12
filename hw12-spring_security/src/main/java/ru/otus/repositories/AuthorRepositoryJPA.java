package ru.otus.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.entities.Author;

import java.util.Optional;

/*
 * @created 12/12 - otus-spring
 * @author Ilya Rogatkin
 */
public interface AuthorRepositoryJPA extends CrudRepository<Author, Long> {
    Optional<Author> findByName(String name);
}
