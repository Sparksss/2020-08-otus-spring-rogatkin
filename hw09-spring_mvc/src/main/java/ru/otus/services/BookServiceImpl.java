package ru.otus.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.entities.Author;
import ru.otus.entities.Book;
import ru.otus.entities.Genre;
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
    private final GenreRepositoryJPA genreRepositoryJPA;
    private final AuthorRepositoryJPA authorRepositoryJPA;

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
    public void update(Book book) throws Exception {
        if(book.getId() == 0) throw new Exception("wrong parameter bookId");
        if(book.getName() == null || book.getName().isEmpty()) throw new Exception("Wrong parameter bookName");
        Book findBook = this.bookRepository.findById(book.getId());
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
    public Book findById(long id) throws Exception {
        if(id == 0) throw new Exception("Wrong parameter id");
        Book book = this.bookRepository.findById(id);
        if(book == null) throw new Exception(String.format("%s%d%s", "Book with id: ", id, "does not exists"));
        return book;
    }

    @Transactional(readOnly = true)
    @Override
    public Long getCountBooks() {
        return this.bookRepository.count();
    }

    @Transactional
    @Override
    public void addAuthorToBook(long authorId, long bookId) throws Exception {
        if(authorId == 0) throw new Exception("Wrong author id");
        if(bookId == 0) throw new Exception("Wrong book id");
        Book book = this.bookRepository.findById(bookId);
        if(book == null) throw new Exception("Book with id: " + bookId + "not found");
        Author author = this.authorRepositoryJPA.findById(authorId);
        if(author == null) throw new Exception("Author with id: " + authorId + "not found");
        if(book.getAuthors() == null || book.getAuthors().isEmpty()) {
            Set<Author> authorSet = new HashSet<>();
            authorSet.add(author);
            book.setAuthors(authorSet);
        } else {
            book.getAuthors().add(author);
        }
        this.bookRepository.save(book);
    }

    private boolean isCorrectValue(String value) {
        return value != null && !value.isEmpty();
    }
}
