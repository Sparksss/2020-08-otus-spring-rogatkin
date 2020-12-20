package ru.otus.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.otus.entity.Book;
import ru.otus.exceptions.NotFoundException;
import ru.otus.exceptions.ValidationException;
import ru.otus.repositories.BookRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/*
 * @created 20/12 - otus-spring
 * @author Ilya Rogatkin
 */
@DisplayName("Сервис BookServiceImpl должен")
@SpringBootTest
class BookServiceImplTest {


    @Configuration
    @Import(BookServiceImpl.class)
    static class Config {}


    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private BookServiceImpl bookService;

    @DisplayName("Добавлять новую книгу")
    @Test
    public void addNewBook() throws ValidationException {
        final String bookName = "New Amazing Story";
        final String genreName = "Novel";
        Book actualBook = Book.builder()
                .name(bookName)
                .genre(genreName)
                .build();
        Mockito.when(bookRepository.save(actualBook)).thenReturn(actualBook);

        Book expectedBook = bookService.add(actualBook);

        assertEquals(expectedBook.getName(), actualBook.getName());
    }


    @DisplayName("Менять название книги")
    @Test
    void updateBookName() throws NotFoundException, ValidationException {
        final String bookName = "Simple roman";
        final String bookId = "1";
        Book actualBook = Book.builder()
                .bookId(bookId)
                .name(bookName)
                .build();

        Mockito.when(bookRepository.save(actualBook)).thenReturn(actualBook);
        Mockito.when(bookRepository.findById(bookId)).thenReturn(Optional.of(actualBook));

        actualBook.setName("New Name book");
        this.bookService.update(actualBook);
        Book expectedBook = this.bookService.findById(bookId);

        assertEquals(expectedBook.getName(), actualBook.getName());
    }


    @DisplayName("Найти книгу по ID")
    @Test
    public void findBookById() throws NotFoundException, ValidationException {
        final String bookId = "1";
        final String bookName = "Simple name";

        Book actualBook = Book.builder()
                .bookId(bookId)
                .name(bookName)
                .build();

        Mockito.when(bookRepository.findById(bookId)).thenReturn(Optional.ofNullable(actualBook));

        Book expectedBook = this.bookService.findById(bookId);

        assertEquals(expectedBook.getBookId(), actualBook.getBookId());
        assertEquals(expectedBook.getName(), actualBook.getName());
    }

    @DisplayName("Найти книгу по названию")
    @Test
    public void findBookByName() throws NotFoundException, ValidationException {
        final String bookName = "Super book";
        Book actualBook = Book.builder()
                .name(bookName)
                .build();
        Mockito.when(bookRepository.findByName(bookName)).thenReturn(actualBook);

        Book expectedBook = this.bookService.findByName(bookName);

        assertEquals(expectedBook.getName(), actualBook.getName());

    }

    @DisplayName("Получать список всех книг")
    @Test
    void findAllBooksInLibrary() {
        Book firstBook = Book.builder()
                .bookId("1")
                .name("Excited Name")
                .genre("Novel")
                .build();
        Book secondBook = Book.builder()
                .bookId("2")
                .name("Amazing Book")
                .genre("Sci-fy")
                .build();

        List<Book> books = Arrays.asList(firstBook, secondBook);

        Mockito.when(this.bookRepository.findAll()).thenReturn(books);

        int idx = 0;

        for(Book book : this.bookRepository.findAll()) {
            assertEquals(book.getBookId(), books.get(idx).getBookId());
            assertEquals(book.getName(), books.get(idx).getName());
            idx++;
        }
    }
}