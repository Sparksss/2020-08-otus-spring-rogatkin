package ru.otus.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.domains.Author;
import ru.otus.domains.Book;
import ru.otus.domains.Genre;
import ru.otus.services.AuthorService;
import ru.otus.services.BookService;
import ru.otus.services.GenreService;

import java.util.List;

@ShellComponent
public class BookShell {

    private BookService bookService;
    private GenreService genreService;
    private AuthorService authorService;

    @Autowired
    public BookShell(BookService bookService, GenreService genreService, AuthorService authorService) {
        this.bookService = bookService;
        this.genreService = genreService;
        this.authorService = authorService;
    }


    @ShellMethod(value = "Find all books", key = {"all"})
    public void all() {
        List<Book> books = bookService.findAll();
        for(Book book : books) {
            System.out.println(book);
        }
    }

    @ShellMethod(value = "add new book", key = {"add"})
    public void add(@ShellOption String bookName, @ShellOption String genreName) throws Exception {
        Genre genre = this.genreService.getByName(genreName);
        Book book = new Book(bookName);
        book.setGenre(genre);
        bookService.save(book);
    }

    @ShellMethod(value = "add author to book", key = {"add_author"})
    public void addAuthor(@ShellOption long bookId, long authorId) throws Exception {
        Author author = this.authorService.findById(authorId);
        Book book = this.bookService.findById(bookId);
        this.bookService.addAuthorToBook(book, author);
    }

    @ShellMethod(value = "change book name", key = {"change_book_name"})
    public void update(@ShellOption long bookId, String bookName) throws Exception {
        Book book = this.bookService.findById(bookId);
        book.setName(bookName);
        this.bookService.update(book);
    }

    @ShellMethod(value = "find book by id", key = {"find_by_id"})
    public void findById(@ShellOption long id) throws Exception {
        Book book = this.bookService.findById(id);
        System.out.println(book);
    }

    @ShellMethod(value = "find books by Author", key = {"find_by_author"})
    public void findByAuthor(@ShellOption long authorId) throws Exception {
        Author author = this.authorService.findById(authorId);
        List<Book> books = this.bookService.getBooksByAuthor(author);
        for (Book book : books) {
            System.out.println(book);
        }
    }
}
