package ru.otus.dao;

import ru.otus.domains.Genre;

import java.util.List;

/**
 * Created by ilya on Oct, 2020
 */
public interface GenreDao {
    int count();
    Genre getById(short id);
    void insert(Genre genre);
    void update(Genre genre);
    List<Genre> getAll();
}
