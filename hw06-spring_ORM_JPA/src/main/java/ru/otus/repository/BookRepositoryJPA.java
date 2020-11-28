package ru.otus.repository;

import ru.otus.models.Book;

import java.util.List;

public interface BookRepositoryJPA {
    Book save(Book book);
    Book findById(long id);
    Book findByName(String name);
    List<Book> findAll();
    void delete(Book book);
    Long countBook();
}
