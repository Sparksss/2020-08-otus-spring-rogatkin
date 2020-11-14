package ru.otus.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.models.Book;
import ru.otus.services.BookService;

import java.util.List;

/*
 * @created 14/11 - otus-spring
 * @author Ilya Rogatkin
 */
@ShellComponent
public class BookShellImpl implements BookShell {

    private BookService bookService;

    public BookShellImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @ShellMethod(value = "find all books", key = {"all_books"})
    @Override
    public void all() {
        List<Book> books = bookService.findAll();
        for(Book book : books) {
            System.out.println(book);
        }
    }

    @ShellMethod(value = "add new book", key = {"add_book"})
    @Override
    public void add(@ShellOption String bookName, @ShellOption String genreName) {
        try {
            this.bookService.addBook(bookName, genreName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @ShellMethod(value = "change name of existing book by id", key = {"update_book"})
    @Override
    public void update(@ShellOption long bookId, @ShellOption String bookName) {
        try {
            this.bookService.update(bookId, bookName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @ShellMethod(value = "find book by id", key = {"find_book"})
    @Override
    public void findById(@ShellOption long id) {
        try {
            Book book = this.bookService.findById(id);
            System.out.println(book);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void findByAuthor(long authorId) {

    }
}
