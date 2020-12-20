package ru.otus.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.entity.Book;
import ru.otus.exceptions.*;
import ru.otus.repositories.BookRepository;

import java.util.ArrayList;
import java.util.List;


/*
 * @created 16/12 - otus-spring
 * @author Ilya Rogatkin
 */
@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Book add(Book book) throws ValidationException {
        if(this.checkBookOnNull(book)) throw new ValidationException("Please add book parameters");
        return bookRepository.save(book);
    }

    @Override
    public void update(Book book) throws ValidationException, NotFoundException {
        if(this.checkBookOnNull(book)) throw new ValidationException("Please add book parameters");
        Book foundBook = this.bookRepository.findById(book.getBookId()).get();
        if(foundBook == null) throw new NotFoundException(String.format("%s%s%s", "Book with id: ", book.getBookId(), "not found"));
        bookRepository.save(foundBook);
    }

    @Override
    public void delete(Book book) throws ValidationException, NotFoundException {
        if(this.checkBookOnNull(book)) throw new ValidationException("Please add book parameters");
        bookRepository.deleteById(book.getBookId());
    }


    @Override
    public Book findById(String bookId) throws ValidationException, NotFoundException {
        if(bookId == null || bookId.isEmpty()) throw new ValidationException("Please add book parameters");
        Book foundBook = this.bookRepository.findById(bookId).get();
        return foundBook;
    }

    @Override
    public Book findByName(String name) throws ValidationException, NotFoundException {
        if(name == null || name.isEmpty()) throw new ValidationException("Please add book parameters");
        Book foundBook = this.bookRepository.findByName(name);
        if(foundBook == null) throw new NotFoundException("Book with name: " + name + "not found");
        return foundBook;
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);
        return books;
    }

    private boolean checkBookOnNull(Book book) {
        return book == null;
    }
}
