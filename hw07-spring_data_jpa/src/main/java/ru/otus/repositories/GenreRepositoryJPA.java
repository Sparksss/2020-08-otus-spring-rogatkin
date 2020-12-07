package ru.otus.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.entities.Genre;

import java.util.List;

/*
 * @created 07/12 - otus-spring
 * @author Ilya Rogatkin
 */
public interface GenreRepositoryJPA extends CrudRepository<Genre, Long> {
    Genre save(Genre genre);
    void delete(Genre genre);
    Genre findById(long id);
    Genre findByName(String name);
    List<Genre> findAll();
    long count();
}
