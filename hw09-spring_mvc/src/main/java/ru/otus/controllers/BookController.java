package ru.otus.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.entities.Book;
import ru.otus.entities.Genre;
import ru.otus.services.BookService;
import ru.otus.services.GenreService;

import java.util.List;

/*
 * @created 05/12 - otus-spring
 * @author Ilya Rogatkin
 */
@RequiredArgsConstructor
@Controller
public class BookController {

    private final BookService bookService;
    private final GenreService genreService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewBook(Model model) throws Exception {
        List<Book> books = this.bookService.findAll();
        model.addAttribute("books", books);
        return "list";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editPage(@RequestParam(value = "id") long id, Model model) throws Exception {
        Book book = this.bookService.findById(id);
        List<Genre> genres = this.genreService.findAll();
        model.addAttribute("book", book);
        model.addAttribute("genres", genres);
        return "edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public RedirectView editBook(Book book) throws Exception {
        this.bookService.update(book);
        return new RedirectView("/");
    }
}
