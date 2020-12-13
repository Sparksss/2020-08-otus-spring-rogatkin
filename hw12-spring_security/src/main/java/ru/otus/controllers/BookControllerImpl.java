package ru.otus.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.entities.Book;
import ru.otus.entities.Genre;
import ru.otus.exceptions.NotFoundException;
import ru.otus.exceptions.ValidateException;
import ru.otus.services.BookService;
import ru.otus.services.GenreService;

import java.util.List;

/*
 * @created 13/12 - otus-spring
 * @author Ilya Rogatkin
 */
@RequiredArgsConstructor
@Controller
public class BookControllerImpl implements BookController {

    private final BookService bookService;
    private final GenreService genreService;

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public String viewBooks(Model model) {
        List<Book> books = this.bookService.findAll();
        model.addAttribute("books", books);
        return "list";
    }

    @RequestMapping(value = "/book/page/add", method = RequestMethod.GET)
    public String addBookPage(Model model) {
        List<Genre> genres = this.genreService.findAll();
        model.addAttribute("genres", genres);
        return "add";
    }

    @RequestMapping(value = "/book/add", method = RequestMethod.POST)
    public RedirectView addBook(Book book) throws ValidateException {
        this.bookService.addBook(book);
        return new RedirectView("/");
    }

    @RequestMapping(value = "/book/page/edit", method = RequestMethod.GET)
    public String editBookPage(@RequestParam(value = "id") long id, Model model) throws ValidateException, NotFoundException {
        Book book = this.bookService.findById(id);
        List<Genre> genres = this.genreService.findAll();
        model.addAttribute("book", book);
        model.addAttribute("genres", genres);
        return "edit";
    }

    @RequestMapping(value = "/book/edit", method = RequestMethod.POST)
    public RedirectView editBook(Book book) throws ValidateException, NotFoundException {
        this.bookService.update(book);
        return new RedirectView("/");
    }

    @RequestMapping(value = "/book/delete", method = RequestMethod.GET)
    public RedirectView deleteBook(Book book) throws ValidateException, NotFoundException {
        this.bookService.delete(book.getId());
        return new RedirectView("/");
    }
}
