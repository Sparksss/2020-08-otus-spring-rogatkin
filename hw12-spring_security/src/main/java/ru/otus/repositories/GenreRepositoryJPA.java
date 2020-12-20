package ru.otus.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.entities.Genre;

import java.util.Optional;

/*
 * @created 12/12 - otus-spring
 * @author Ilya Rogatkin
 */
public interface GenreRepositoryJPA extends CrudRepository<Genre, Long> {
    Optional<Genre> findByName(String name);
}
