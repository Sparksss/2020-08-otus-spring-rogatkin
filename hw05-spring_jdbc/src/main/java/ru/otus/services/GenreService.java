package ru.otus.services;

import ru.otus.domains.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAll();
    Genre findById(long id) throws Exception;
    Genre getByName(String name) throws Exception;
    int countAll();
    void save(Genre genre) throws Exception;
    void update(long id, String genreName) throws Exception;
    void delete(long id) throws Exception;
}
