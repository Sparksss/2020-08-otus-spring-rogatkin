package ru.otus.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.models.Book;
import ru.otus.services.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/*
 * @created 14/11 - otus-spring
 * @author Ilya Rogatkin
 */
@ShellComponent
public class BookShellImpl implements BookShell {

    private BookService bookService;

    private static Logger logger = Logger.getLogger("BookLogger");

    @Autowired
    public BookShellImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @ShellMethod(value = "find all books", key = {"all_books"})
    @Override
    public void all() {
        try {
            List<Book> books = bookService.findAll();
            if(books.size() > 0) {
                for(Book book : books) {
                    System.out.println(book);
                }
            } else {
                System.out.println("Book not found in library");
            }
        } catch (Exception e) {
            logger.warning(e.getMessage());
        }
    }

    @ShellMethod(value = "add new book", key = {"add_book"})
    @Override
    public void add(@ShellOption String bookName, @ShellOption String genreName) {
        try {
            this.bookService.addBook(bookName, genreName);
        } catch (Exception e) {
            logger.warning(e.getMessage());
        }
    }

    @ShellMethod(value = "change name of existing book by id", key = {"update_book"})
    @Override
    public void update(@ShellOption long bookId, @ShellOption String bookName) {
        try {
            this.bookService.update(bookId, bookName);
        } catch (Exception e) {
            logger.warning(e.getMessage());
        }
    }

    @ShellMethod(value = "find book by id", key = {"find_book"})
    @Override
    public void findById(@ShellOption long id) {
        try {
            Book book = this.bookService.findById(id);
            System.out.println(book);
        } catch (Exception e) {
            logger.warning(e.getMessage());
        }
    }

    @ShellMethod(value = "add list authors to book", key = {"add_authors_to_book"})
    @Override
    public void addAuthorToBook(@ShellOption long authorId, @ShellOption long bookId) {
        try {
            this.bookService.addAuthorToBook(authorId, bookId);
        } catch (Exception e) {
            logger.warning(e.getMessage());
        }
    }
}
