package ru.otus.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.entities.Book;
import ru.otus.exceptions.NotFoundException;
import ru.otus.exceptions.ValidateException;
import ru.otus.services.BookService;

import java.util.List;

/*
 * @created 05/12 - otus-spring
 * @author Ilya Rogatkin
 */
@RequiredArgsConstructor
@RestController
public class BookControllerImpl {

    private final BookService bookService;

    @GetMapping("/book")
    public List<Book> getBooks() {
        List<Book> books = this.bookService.findAll();
        return books;
    }

    @GetMapping(value = "/book/{id}")
    public Book getBookById(@PathVariable("id") long id) {
        Book book = this.bookService.findById(id);
        return book;
    }

    @PostMapping(value = "/book")
    public Book addBook(@RequestBody Book book) throws ValidateException, NotFoundException {
        return this.bookService.addBook(book);
    }

    @PutMapping(value = "/book")
    public Book editBook(@RequestBody Book book) throws ValidateException, NotFoundException {
        return this.bookService.update(book);
    }

    @DeleteMapping(value = "/book")
    public void deleteBook(@RequestBody Book book) throws ValidateException, NotFoundException {
        this.bookService.delete(book);
    }
}
