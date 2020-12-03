package ru.otus.repository;

import ru.otus.models.Genre;

import java.util.List;

/*
 * @created 14/11 - otus-spring
 * @author Ilya Rogatkin
 */
public interface GenreRepositoryJPA {
    Genre save(Genre genre);
    void delete(Genre genre);
    Genre findById(long id);
    Genre findByName(String name);
    List<Genre> findAll();
    Long count();
}
