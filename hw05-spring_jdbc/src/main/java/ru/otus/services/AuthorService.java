package ru.otus.services;

import ru.otus.domains.Author;

import java.util.List;

public interface AuthorService {
    void save(String authorName) throws Exception;
    void update(long id, String authorName) throws Exception;
    void delete(long id) throws Exception;
    List<Author> findAll();
    Author findById(long id) throws Exception;
}
