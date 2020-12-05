package ru.otus.services;

import ru.otus.entities.Genre;

import java.util.List;

/*
 * @created 14/11 - otus-spring
 * @author Ilya Rogatkin
 */
public interface GenreService {
    List<Genre> findAll();
    Genre findById(long id) throws Exception;
    Genre getByName(String name) throws Exception;
    Long countAll();
    void save(String genreName) throws Exception;
    void update(long id, String genreName) throws Exception;
    void delete(long id) throws Exception;
}
