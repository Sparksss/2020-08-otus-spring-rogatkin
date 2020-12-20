package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.entity.Book;
import ru.otus.exceptions.NotFoundException;
import ru.otus.exceptions.ValidationException;
import ru.otus.services.BookService;

/*
 * @created 20/12 - otus-spring
 * @author Ilya Rogatkin
 */
@RequiredArgsConstructor
@ShellComponent
public class BookShell {

    private final BookService bookService;

    private Logger logger = LoggerFactory.getLogger(BookShell.class);


    @ShellMethod(value = "add new book", key = {"add_book"})
    public void add(@ShellOption String bookName, @ShellOption String genreName) {
        try {
            Book book = Book.
                    builder()
                    .name(bookName)
                    .genre(genreName)
                    .build();

            bookService.add(book);

        } catch (ValidationException e) {

            logger.info(e.getMessage());

        }
    }

    @ShellMethod(value = "update book", key = {"update_book"})
    public void update(@ShellOption String bookId, @ShellOption String bookName) {
        try {
            Book book = Book.builder()
                    .bookId(bookId)
                    .name(bookName)
                    .build();
            bookService.update(book);
        } catch (ValidationException | NotFoundException e) {
            logger.info(e.getMessage());
        }
    }

    @ShellMethod(value = "find by id", key = {"find_book_by_id"})
    public void findById(@ShellOption String bookId) {
        try {
            System.out.println(bookService.findById(bookId));
        } catch (ValidationException | NotFoundException e) {
            logger.info(e.getMessage());
        }
    }

    @ShellMethod(value = "find by name", key = {"find_book_by_name"})
    public void findByName(@ShellOption String name) {
        try {
            System.out.println(bookService.findByName(name));
        } catch (ValidationException | NotFoundException e) {
            logger.info(e.getMessage());
        }
    }


    @ShellMethod(value = "show all books", key = {"all_books"})
    public void all() {
        for(Book book : this.bookService.findAll()) {
            System.out.println(book);
        }
    }
}
