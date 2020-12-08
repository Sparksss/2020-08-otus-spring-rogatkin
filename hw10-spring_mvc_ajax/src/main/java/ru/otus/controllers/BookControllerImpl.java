package ru.otus.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return this.bookService.findById(id);
    }

    @PostMapping(value = "/book")
    public Book addBook(@RequestBody Book book) throws ValidateException, NotFoundException {
        return this.bookService.addBook(book);
    }

    @PutMapping(value = "/book")
    public Book editBook(@RequestBody Book book) throws ValidateException, NotFoundException {
        return this.bookService.update(book);
    }

    @DeleteMapping(value = "/book/{id}")
    public ResponseEntity deleteBookById(@PathVariable long id) throws ValidateException, NotFoundException {
        this.bookService.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
