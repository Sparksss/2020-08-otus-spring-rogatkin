package ru.otus.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.entities.Author;
import ru.otus.entities.Book;
import ru.otus.entities.Genre;
import ru.otus.exceptions.NotFoundException;
import ru.otus.exceptions.ValidateException;
import ru.otus.repositories.AuthorRepositoryJPA;
import ru.otus.repositories.BookRepositoryJPA;
import ru.otus.repositories.GenreRepositoryJPA;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * @created 14/11 - otus-spring
 * @author Ilya Rogatkin
 */
@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepositoryJPA bookRepository;
    private final AuthorRepositoryJPA authorRepositoryJPA;
    private final GenreRepositoryJPA genreRepositoryJPA;

    @Transactional
    @Override
    public Book addBook(Book book) throws ValidateException {
        if(!this.isCorrectValue(book.getName())) throw new ValidateException("Please enter a book name");
        if(book.getGenre() == null) throw new ValidateException("Please select a genre");
        if(book.getGenre().getId() == 0) throw new ValidateException("Please select a genre");
        Genre genre = this.genreRepositoryJPA.findById(book.getGenre().getId())
                .orElseThrow(() -> {throw new NotFoundException("Genre with id: " + book.getGenre().getId() + "not found");});
        book.setGenre(genre);
        return this.bookRepository.save(book);
    }

    @Transactional
    @Override
    public Book update(Book book) throws ValidateException, NotFoundException {
        if(book.getId() == 0) throw new ValidateException("wrong parameter bookId");
        if(book.getName() == null || book.getName().isEmpty()) throw new ValidateException("Wrong parameter bookName");
        if(this.bookRepository.existsById(book.getId())) {
           return this.bookRepository.save(book);
        } else {
            throw new NotFoundException("Book with id: " + book.getId() + "NotFound");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        this.bookRepository.findAll().forEach(books::add);
        return books;
    }

    @Transactional(readOnly = true)
    @Override
    public Book findById(long id) throws ValidateException, NotFoundException {
        if(id == 0) throw new ValidateException("Wrong parameter id");
            return this.bookRepository.findById(id)
                    .orElseThrow(() -> {throw new NotFoundException(String.format("%s%d%s", "Book with id: ", id, "does not exists"));});
    }

    @Transactional(readOnly = true)
    @Override
    public Long getCountBooks() {
        return this.bookRepository.count();
    }

    @Transactional
    @Override
    public void addAuthorToBook(long authorId, long bookId) throws ValidateException, NotFoundException {
        if(authorId == 0) throw new ValidateException("Wrong author id");
        if(bookId == 0) throw new ValidateException("Wrong book id");
        Book book = this.bookRepository.findById(bookId)
                .orElseThrow(() -> {throw new NotFoundException("Book with id: " + bookId + "not found");});
        Author author = this.authorRepositoryJPA.findById(authorId)
                .orElseThrow(() -> {throw new NotFoundException("Author with id: " + authorId + "not found");});
        if(book.getAuthors() == null || book.getAuthors().isEmpty()) {
            Set<Author> authorSet = new HashSet<>();
            authorSet.add(author);
            book.setAuthors(authorSet);
        } else {
            book.getAuthors().add(author);
        }
        this.bookRepository.save(book);
    }

    @Transactional
    @Override
    public void delete(long bookId) throws ValidateException, NotFoundException {
        if(bookId == 0) throw new ValidateException("Wrong book id");
        Book book = this.bookRepository.findById(bookId)
                .orElseThrow(() -> {throw new NotFoundException("Book with id: " + bookId + "not found");});
        this.bookRepository.delete(book);
    }

    private boolean isCorrectValue(String value) {
        return value != null && !value.isEmpty();
    }
}
