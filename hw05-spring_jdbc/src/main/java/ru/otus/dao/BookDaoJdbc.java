package ru.otus.dao;

import ru.otus.domains.Author;
import ru.otus.domains.Book;
import ru.otus.domains.Genre;

import java.util.List;

/**
 * Created by ilya on Oct, 2020
 */
public interface BookDaoJdbc {
    int count();
    Book insert(Book book);
    void update(Book book);
    void delete(Book book);
    Book getById(long id);
    Book getByName(String name);
    List<Book> getAll();
    List<Book> getByGenre(Genre genre);
    List<Book> getByAuthor(Author author);
}
