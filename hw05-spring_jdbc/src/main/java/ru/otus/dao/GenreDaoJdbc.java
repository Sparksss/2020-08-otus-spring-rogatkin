package ru.otus.dao;

import ru.otus.domains.Genre;

import java.util.List;

/**
 * Created by ilya on Oct, 2020
 */
public interface GenreDaoJdbc {
    int count();
    Genre getById(long id);
    Genre getByName(String name);
    void insert(Genre genre);
    void update(Genre genre);
    void delete(Genre genre);
    List<Genre> getAll();
}
