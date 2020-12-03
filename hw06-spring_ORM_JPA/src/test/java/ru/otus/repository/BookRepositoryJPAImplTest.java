package ru.otus.repository;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.models.Book;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/*
 * @created 25/11 - otus-spring
 * @author Ilya Rogatkin
 */
@DisplayName("Репозиторий BookRepositoryJPA должен")
@DataJpaTest
@Import({BookRepositoryJPAImpl.class})
class BookRepositoryJPAImplTest {

    private final Long FIRST_BOOK_ID = 1L;
    private final String BOOK_NAME = "The Captains Daughter";
    private final Long INITIAL_COUNT_BOOKS = 1L;


    @Autowired
    private BookRepositoryJPAImpl bookRepositoryJPA;

    @DisplayName("Находить книгу по ID")
    @Test
    public void findBookById() {
        Book book = this.bookRepositoryJPA.findById(FIRST_BOOK_ID);
        assertThat(book).isNotNull().hasFieldOrPropertyWithValue("id", FIRST_BOOK_ID);
    }

    @DisplayName("Находить книгу по названию")
    @Test
    public void findBookByName() {
        Book book = this.bookRepositoryJPA.findByName(BOOK_NAME);
        assertThat(book).isNotNull().hasFieldOrPropertyWithValue("name", BOOK_NAME);
    }

    @DisplayName("Получать все книги")
    @Test
    public void getAllBooks() {
        List<Book> books = this.bookRepositoryJPA.findAll();
        assertThat(books).isNotNull();
        assertEquals(books.size(), INITIAL_COUNT_BOOKS);
    }

    @DisplayName("Получать общее количество книг")
    @Test
    public void countBook() {
        Long actualCount = this.bookRepositoryJPA.countBook();
        assertEquals(INITIAL_COUNT_BOOKS, actualCount);
    }

    @DisplayName("Добавлять новую книгу")
    @Test
    public void addNewBook() {
        final String NEW_BOOK_NAME = "The Queen of Spades";
        Book book = new Book(NEW_BOOK_NAME);
        Book actualBook = this.bookRepositoryJPA.save(book);
        assertEquals(NEW_BOOK_NAME, actualBook.getName());
    }

    @DisplayName("Изменять название книги")
    @Test
    public void updateNameById() {
        final String NEW_NAME_OF_BOOK = "Ruslan and Ludmila";
        Book expectedBook = this.bookRepositoryJPA.findById(FIRST_BOOK_ID);
        expectedBook.setName(NEW_NAME_OF_BOOK);
        Book actualBook = this.bookRepositoryJPA.save(expectedBook);
        assertEquals(actualBook.getName(), expectedBook.getName());
    }
}