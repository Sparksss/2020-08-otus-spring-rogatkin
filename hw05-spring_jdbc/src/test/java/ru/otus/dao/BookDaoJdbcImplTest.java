package ru.otus.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.domains.Book;
import ru.otus.domains.Genre;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by ilya on Oct, 2020
 */
@JdbcTest
@Import({BookDaoJdbcImpl.class})
@DisplayName("BookDaoJdbc")
class BookDaoJdbcImplTest {

    @Autowired
    BookDaoJdbcImpl bookDaoJdbc;

    @Autowired
    GenreDaoJdbcImpl genreDaoJdbc;

    private static final long INITIAL_COUNT_BOOKS = 1;
    private static final String GENRE = "Comedy";

    @Test
    @DisplayName("Проверяет начальное количество записей в таблице")
    void count() {
        assertEquals(INITIAL_COUNT_BOOKS, bookDaoJdbc.count());
    }

    @Test
    @DisplayName("Добавляем новую книгу")
    void insert() {
        Book book = new Book("War and Peace");
        bookDaoJdbc.insert(book);
        assertEquals(INITIAL_COUNT_BOOKS + 1, bookDaoJdbc.count());
    }

    @Test
    @DisplayName("Достаёт строку из таблицы")
    void getById() {
        Book book = bookDaoJdbc.getById(INITIAL_COUNT_BOOKS);
        assertThat(book).isNotNull().hasFieldOrPropertyWithValue("id", INITIAL_COUNT_BOOKS);
    }

    @Test
    @DisplayName("Проверяет общее количество записей в БД")
    void getAll() {
        List<Book> books = bookDaoJdbc.getAll();
        assertEquals(bookDaoJdbc.count(), books.size());
    }

    @Test
    @DisplayName("Достаёт все книги определённого жанра")
    void getByGenre() {
        Genre comedy = genreDaoJdbc.getByName(GENRE);
        List<Book> books = bookDaoJdbc.getByGenre(comedy);
        assertNotEquals(0, books.size());
    }
//
//    @Test
//    void getByAuthor() {
//    }
}