package ru.otus.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.dto.BookDto;
import ru.otus.models.Book;
import ru.otus.services.BookService;

import java.util.ArrayList;
import java.util.List;

/*
 * @created 14/11 - otus-spring
 * @author Ilya Rogatkin
 */
@ShellComponent
public class BookShellImpl implements BookShell {

    private BookService bookService;

    @Autowired
    public BookShellImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @ShellMethod(value = "find all books", key = {"all_books"})
    @Override
    public void all() {
        List<Book> books = new ArrayList<>();
        try {
            books = bookService.findAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
        }

        if(books.size() == 0) {
            System.out.println("Book bot found in library");
        } else {
            for(Book book : books) {
                System.out.println(book);
            }
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
            BookDto book = this.bookService.findById(id);
            System.out.println(book);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @ShellMethod(value = "find books by author", key = {"find_book_by_author"})
    @Override
    public void findByAuthor(@ShellOption long authorId) {
        try {
            for(Book book : this.bookService.getBooksByAuthor(authorId)) {
                System.out.println(book);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @ShellMethod(value = "find books by genre", key = {"find_book_by_genre"})
    @Override
    public void findByGenre(@ShellOption long genreId) {
        try {
            for(Book book : this.bookService.getBooksByGenre(genreId)) {
                System.out.println(book);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @ShellMethod(value = "add author to book", key = {"add_author_to_book"})
    @Override
    public void addAuthorToBook(@ShellOption long bookId,@ShellOption long authorId) {
        try {
            this.bookService.addAuthorToBook(bookId, authorId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @ShellMethod(value = "add new comment to book", key = {"add_comment_to_book"})
    @Override
    public void addCommentToBook(@ShellOption long bookId, @ShellOption String commentText) {
        try {
            this.bookService.addCommentToBook(bookId, commentText);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
