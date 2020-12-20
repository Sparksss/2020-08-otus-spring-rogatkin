package ru.otus.services;

import ru.otus.entities.Genre;
import ru.otus.exceptions.NotFoundException;
import ru.otus.exceptions.ValidateException;

import java.util.List;

/*
 * @created 13/12 - otus-spring
 * @author Ilya Rogatkin
 */
public interface GenreService {
    List<Genre> findAll();
    Genre findById(long id) throws ValidateException;
    Genre getByName(String name) throws ValidateException, NotFoundException;
    Long countAll();
    void save(String genreName) throws ValidateException;
    void update(long id, String genreName) throws ValidateException, NotFoundException;
    void delete(long id) throws ValidateException, NotFoundException;
}
