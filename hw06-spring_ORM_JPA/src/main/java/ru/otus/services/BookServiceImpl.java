package ru.otus.services;

import org.springframework.stereotype.Component;
import ru.otus.models.Author;
import ru.otus.models.Book;
import ru.otus.models.Genre;
import ru.otus.repository.BookRepositoryJPA;
import ru.otus.repository.GenreRepositoryJPA;

import java.util.List;

/*
 * @created 14/11 - otus-spring
 * @author Ilya Rogatkin
 */
@Component
public class BookServiceImpl implements BookService {

    private BookRepositoryJPA bookRepository;
    private GenreRepositoryJPA genreRepositoryJPA;

    public BookServiceImpl(BookRepositoryJPA bookRepositoryJPA, GenreRepositoryJPA genreRepositoryJPA) {
        this.bookRepository = bookRepositoryJPA;
        this.genreRepositoryJPA = genreRepositoryJPA;
    }

    @Override
    public void addBook(String bookName, String genreName) throws Exception {
        Genre genre = this.genreRepositoryJPA.findByName(genreName);
        Book book = new Book(bookName);
        book.setGenre(genre);
        this.bookRepository.save(book);
    }

    @Override
    public void addAuthorToBook(Book book, Author author) throws Exception {

    }

    @Override
    public void update(long bookId, String bookName) throws Exception {
        if(bookId == 0) throw new Exception("wrong parameter bookId");
        if(bookName == null || bookName.isEmpty()) throw new Exception("Wrong parameter bookName");
        Book book = this.bookRepository.findById(bookId);
        book.setName(bookName);
        this.bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book findById(long id) throws Exception {
        return this.bookRepository.findById(id);
    }

    @Override
    public int getCountBooks() {
        return 0;
    }

    @Override
    public List<Book> getBooksByAuthor(Author author) throws Exception {
        return null;
    }

    @Override
    public List<Book> getBooksByGenre(String genreName) throws Exception {
        return null;
    }
}
