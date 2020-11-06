package ru.otus.services;

import ru.otus.domains.Author;
import ru.otus.domains.Book;

import java.util.List;

public interface BookService {
    void save(Book book) throws Exception;
    void addAuthorToBook(Book book, Author author) throws Exception;
    void update(Book book) throws Exception;
    List<Book> findAll();
    Book findById(long id) throws Exception;
    int getCountBooks();
    List<Book> getBooksByAuthor(Author author) throws Exception;
    List<Book> getBooksByGenre(String genreName) throws Exception;
}
