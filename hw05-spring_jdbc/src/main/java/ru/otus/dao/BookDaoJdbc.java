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
    void insert(Book book);
    Book getById(long id);
    List<Book> getAll();
    List<Book> getByGenre(Genre genre);
    List<Book> getByAuthor(Author author);
}
