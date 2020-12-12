package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.entities.Book;
import ru.otus.services.BookService;

import java.util.List;
import java.util.logging.Logger;

/*
 * @created 07/12 - otus-spring
 * @author Ilya Rogatkin
 */
@RequiredArgsConstructor
@ShellComponent
public class BookShellImpl implements BookShell {

    private final BookService bookService;

    private static Logger logger = Logger.getLogger("BookLogger");

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

    @ShellMethod(value = "add author to book", key = {"add_author_to_book"})
    @Override
    public void addAuthorToBook(@ShellOption long authorId, @ShellOption long bookId) {
        try {
            this.bookService.addAuthorToBook(authorId, bookId);
        } catch (Exception e) {
            logger.warning(e.getMessage());
        }
    }

    @ShellMethod(value = "add comment to book", key = {"add_comment_to_book"})
    @Override
    public void addCommentToBook(@ShellOption String commentText, @ShellOption long bookId) {
        try {
            this.bookService.addCommentToBook(commentText, bookId);
        } catch (Exception e) {
            logger.warning(e.getMessage());
        }
    }

    @ShellMethod(value = "count books", key = {"count_books"})
    @Override
    public void countAllBooks() {
        System.out.println(this.bookService.getCountBooks());
    }
}
