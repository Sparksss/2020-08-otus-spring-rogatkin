package ru.otus.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.domains.Author;
import ru.otus.domains.Book;
import ru.otus.domains.Genre;

import java.lang.management.OperatingSystemMXBean;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by ilya on Oct, 2020
 */
@JdbcTest
@Import({BookDaoJdbcImpl.class, GenreDaoJdbcImpl.class, AuthorDaoJdbcImpl.class})
@DisplayName("BookDaoJdbc")
class BookDaoJdbcImplTest {

    @Autowired
    BookDaoJdbcImpl bookDaoJdbc;

    @Autowired
    GenreDaoJdbcImpl genreDaoJdbc;

    @Autowired
    AuthorDaoJdbcImpl authorDaoJdbc;

    private static final long FIRST_BOOK_ID = 1;
    private static final long INITIAL_COUNT_BOOKS = 2;
    private static final String GENRE = "Comedy";
    private static final long FIRST_AUTHOR_ID = 1;

    @Test
    @DisplayName("Проверяет начальное количество записей в таблице")
    public void count() {
        assertEquals(INITIAL_COUNT_BOOKS, bookDaoJdbc.count());
    }

    @Test
    @DisplayName("Добавляем новую книгу без авторов и жанра")
    public void addBookWithoutGenreAndAuthors() {
        Book book = new Book("War and Peace");
        bookDaoJdbc.insert(book);
        assertEquals(INITIAL_COUNT_BOOKS + 1, bookDaoJdbc.count());
    }

    @Test
    @DisplayName("Добавляет новую книгу с жанром")
    public void addBookWithGenre() {
        Genre genre = genreDaoJdbc.getByName(GENRE);
        Book book = new Book("New Book");
        book.setGenre(genre);
        Book book1 = bookDaoJdbc.insert(book);
        assertThat(book1)
                .isNotNull()
                .hasFieldOrPropertyWithValue("name", book.getName());
        assertEquals(genre.getId(), book1.getGenre().getId());
        assertEquals(genre.getName(), book1.getGenre().getName());
    }

    @Test
    @DisplayName("Меняет название книги")
    public void changeBookName() {
        Book expectedBook = this.bookDaoJdbc.getById(FIRST_BOOK_ID);
        expectedBook.setName("New Book Name");
        this.bookDaoJdbc.update(expectedBook);
        Book actualBook = this.bookDaoJdbc.getById(FIRST_BOOK_ID);
        assertEquals(expectedBook.getName(), actualBook.getName());
    }

    @Test
    @DisplayName("Достаёт строку из таблицы")
    public void findBookById() {
        Book book = bookDaoJdbc.getById(FIRST_BOOK_ID);
        assertThat(book).isNotNull().hasFieldOrPropertyWithValue("id", FIRST_BOOK_ID);
    }

    @Test
    @DisplayName("Проверяет общее количество записей в БД")
    public void findAllBook() {
        List<Book> books = bookDaoJdbc.getAll();
        assertEquals(bookDaoJdbc.count(), books.size());
    }

    @Test
    @DisplayName("Достаёт все книги определённого жанра")
    public void findBookByGenre() {
        Genre comedy = genreDaoJdbc.getByName(GENRE);
        List<Book> books = bookDaoJdbc.getByGenre(comedy);
        assertEquals(1, books.size());
    }

    @Test
    @DisplayName("Добавляет автора к книге")
    public void addAuthorToBook() {
        Book book = new Book("My live, My rules");
        Genre genre = this.genreDaoJdbc.getByName(GENRE);
        book.setGenre(genre);
        bookDaoJdbc.insert(book);
        Author author = this.authorDaoJdbc.getById(FIRST_AUTHOR_ID);
        bookDaoJdbc.addAuthorToBooK(book, author);
        book = this.bookDaoJdbc.getById(book.getId());
        Optional<Author> foundAuthor = book.getAuthors().stream().filter(author1 -> author1.getId() == author.getId()).findAny();
        assertTrue(foundAuthor.isPresent());
    }
}