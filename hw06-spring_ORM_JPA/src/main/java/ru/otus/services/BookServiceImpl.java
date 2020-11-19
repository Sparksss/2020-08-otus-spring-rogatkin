package ru.otus.services;

import org.springframework.stereotype.Component;
import ru.otus.models.Book;
import ru.otus.models.BookAuthor;
import ru.otus.models.Genre;
import ru.otus.repository.BookAuthorRepositoryJPA;
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
    private BookAuthorRepositoryJPA bookAuthorRepositoryJPA;

    public BookServiceImpl(BookRepositoryJPA bookRepositoryJPA, GenreRepositoryJPA genreRepositoryJPA) {
        this.bookRepository = bookRepositoryJPA;
        this.genreRepositoryJPA = genreRepositoryJPA;
    }

    @Override
    public void addBook(String bookName, String genreName) throws Exception {
        if(!this.isCorrectValue(bookName)) throw new Exception("Wrong book name");
        if(!this.isCorrectValue(genreName)) throw new Exception("Wrong genre name");
        Genre genre = this.genreRepositoryJPA.findByName(genreName);
        Book book = new Book(bookName);
        book.setGenre(genre);
        this.bookRepository.save(book);
    }

    @Override
    public void addAuthorToBook(long bookId, long authorId) throws Exception {
        if(bookId == 0) throw new Exception("wrong book id");
        if(authorId == 0) throw new Exception("wrong author id");
        BookAuthor bookAuthor = new BookAuthor(bookId, authorId);
        this.bookAuthorRepositoryJPA.save(bookAuthor);
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
        if(id == 0) throw new Exception("Wrong parameter id");
        return this.bookRepository.findById(id);
    }

    @Override
    public int getCountBooks() {
        return this.bookRepository.countBook();
    }

    @Override
    public List<Book> getBooksByAuthor(long authorId) throws Exception {
        if(authorId == 0) throw new Exception("Wrong author id");
        return this.bookRepository.findAllByAuthor(authorId);
    }

    @Override
    public List<Book> getBooksByGenre(long genreId) throws Exception {
        if(genreId == 0) throw new Exception("Wrong genre id");
        return this.bookRepository.findAllByGenre(genreId);
    }

    private boolean isCorrectValue(String value) {
        return value != null && !value.isEmpty();
    }
}
