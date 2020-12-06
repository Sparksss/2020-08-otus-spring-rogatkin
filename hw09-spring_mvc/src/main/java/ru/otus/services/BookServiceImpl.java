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

    @Transactional
    @Override
    public void addBook(Book book) throws ValidateException {
        if(!this.isCorrectValue(book.getName())) throw new ValidateException("Please enter a book name");
        if(book.getGenre().getId() == 0) throw new ValidateException("Please select a genre");
        this.bookRepository.save(book);
    }

    @Transactional
    @Override
    public void update(Book book) throws ValidateException, NotFoundException {
        if(book.getId() == 0) throw new ValidateException("wrong parameter bookId");
        if(book.getName() == null || book.getName().isEmpty()) throw new ValidateException("Wrong parameter bookName");
        Book findBook = this.bookRepository.findById(book.getId());
        if(findBook == null) throw new NotFoundException("Book with id: " + book.getId() + "NotFound");
        findBook.setName(book.getName());
        this.bookRepository.save(book);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Book findById(long id) throws ValidateException, NotFoundException {
        if(id == 0) throw new ValidateException("Wrong parameter id");
        Book book = this.bookRepository.findById(id);
        if(book == null) throw new NotFoundException(String.format("%s%d%s", "Book with id: ", id, "does not exists"));
        return book;
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
        Book book = this.bookRepository.findById(bookId);
        if(book == null) throw new NotFoundException("Book with id: " + bookId + "not found");
        Author author = this.authorRepositoryJPA.findById(authorId);
        if(author == null) throw new NotFoundException("Author with id: " + authorId + "not found");
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
    public void delete(Book book) throws ValidateException {
        if(book == null) throw new ValidateException("Book bot found");
        if(book.getId() == 0) throw new ValidateException("Wrong id for book");
        Book findBook = this.bookRepository.findById(book.getId());
        this.bookRepository.delete(findBook);
    }

    private boolean isCorrectValue(String value) {
        return value != null && !value.isEmpty();
    }
}
