package ru.otus.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.domains.Book;
import ru.otus.domains.Genre;
import ru.otus.services.BookService;
import ru.otus.services.GenreService;

import java.util.List;

@ShellComponent
public class BookShell {

    private BookService bookService;
    private GenreService genreService;

    @Autowired
    public BookShell(BookService bookService, GenreService genreService) {
        this.bookService = bookService;
        this.genreService = genreService;
    }


    @ShellMethod(value = "Find all books", key = {"all"})
    public void all() {
        List<Book> books = bookService.findAll();
        for(Book book : books) {
            System.out.println(book.getName());
        }
    }

    @ShellMethod(value = "add new book", key = {"add"})
    public void add(@ShellOption String bookName, @ShellOption String genreName) throws Exception {
        Genre genre = this.genreService.getByName(genreName);
        Book book = new Book(bookName);
        book.setGenre(genre);
        bookService.save(book);
    }
}
