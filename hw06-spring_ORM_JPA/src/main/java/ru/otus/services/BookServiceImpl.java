package ru.otus.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.dto.BookDto;
import ru.otus.models.Author;
import ru.otus.models.Book;
import ru.otus.models.BookAuthor;
import ru.otus.models.Genre;
import ru.otus.repository.AuthorRepositoryJPA;
import ru.otus.repository.BookAuthorRepositoryJPA;
import ru.otus.repository.BookRepositoryJPA;
import ru.otus.repository.GenreRepositoryJPA;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/*
 * @created 14/11 - otus-spring
 * @author Ilya Rogatkin
 */
@Service
public class BookServiceImpl implements BookService {

    private BookRepositoryJPA bookRepository;
    private GenreRepositoryJPA genreRepositoryJPA;
    private BookAuthorRepositoryJPA bookAuthorRepositoryJPA;
    private AuthorRepositoryJPA authorRepositoryJPA;

    @Autowired
    public BookServiceImpl(BookRepositoryJPA bookRepositoryJPA,
                           GenreRepositoryJPA genreRepositoryJPA,
                           BookAuthorRepositoryJPA bookAuthorRepositoryJPA,
                           AuthorRepositoryJPA authorRepositoryJPA) {
        this.bookRepository = bookRepositoryJPA;
        this.genreRepositoryJPA = genreRepositoryJPA;
        this.bookAuthorRepositoryJPA = bookAuthorRepositoryJPA;
        this.authorRepositoryJPA = authorRepositoryJPA;
    }

    @Transactional
    @Override
    public void addBook(String bookName, String genreName) throws Exception {
        if(!this.isCorrectValue(bookName)) throw new Exception("Wrong book name");
        if(!this.isCorrectValue(genreName)) throw new Exception("Wrong genre name");
        Genre genre = this.genreRepositoryJPA.findByName(genreName);
        Book book = new Book(bookName);
        book.setGenre(genre);
        this.bookRepository.save(book);
    }

    @Transactional
    @Override
    public void addAuthorToBook(long bookId, long authorId) throws Exception {
        if(bookId == 0) throw new Exception("wrong book id");
        if(authorId == 0) throw new Exception("wrong author id");
        BookAuthor bookAuthor = new BookAuthor(bookId, authorId);
        this.bookAuthorRepositoryJPA.save(bookAuthor);
    }

    @Transactional
    @Override
    public void update(long bookId, String bookName) throws Exception {
        if(bookId == 0) throw new Exception("wrong parameter bookId");
        if(bookName == null || bookName.isEmpty()) throw new Exception("Wrong parameter bookName");
        Book book = this.bookRepository.findById(bookId);
        book.setName(bookName);
        this.bookRepository.save(book);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public BookDto findById(long id) throws Exception {
        if(id == 0) throw new Exception("Wrong parameter id");
        Book book = this.bookRepository.findById(id);
        if(book == null) throw new Exception(String.format("%s%d%s", "Book with id: ", id, "does not exists"));
        List<Author> bookAuthors = this.authorRepositoryJPA.getAllAuthorsByBookId(book.getId());
        BookDto bookDto = new BookDto(book.getId(), book.getName(), book.getGenre(), bookAuthors);
        return bookDto;
    }

    @Transactional(readOnly = true)
    @Override
    public Long getCountBooks() {
        return this.bookRepository.countBook();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getBooksByAuthor(long authorId) throws Exception {
        if(authorId == 0) throw new Exception("Wrong author id");
        return this.bookRepository.findAllByAuthor(authorId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getBooksByGenre(long genreId) throws Exception {
        if(genreId == 0) throw new Exception("Wrong genre id");
        return this.bookRepository.findAllByGenre(genreId);
    }

    private boolean isCorrectValue(String value) {
        return value != null && !value.isEmpty();
    }
}
