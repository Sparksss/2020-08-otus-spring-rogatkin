package ru.otus.dao;

import ru.otus.domains.Author;

import java.util.List;

/**
 * Created by ilya on Oct, 2020
 */
public interface AuthorDaoJdbc {
    int count();
    void insert(Author author);
    void update(Author author);
    void delete(Author author);
    Author getById(long id);
    Author getByName(String name);
    List<Author> getAll();
    List<Author> getAllByBookId(long bookId);
}
