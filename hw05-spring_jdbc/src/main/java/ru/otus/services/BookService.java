package ru.otus.services;

import org.springframework.stereotype.Component;
import ru.otus.dao.AuthorDaoJdbc;
import ru.otus.dao.BookDaoJdbc;
import ru.otus.dao.GenreDaoJdbc;
import ru.otus.domains.Author;
import ru.otus.domains.Book;
import ru.otus.domains.Genre;

import java.util.List;

/**
 * Created by ilya on Oct, 2020
 */
@Component
public class BookService {
    BookDaoJdbc bookDaoJdbc;
    AuthorDaoJdbc authorDaoJdbc;
    GenreDaoJdbc genreDaoJdbc;

    public BookService(BookDaoJdbc bookDaoJdbc, AuthorDaoJdbc authorDaoJdbc, GenreDaoJdbc genreDaoJdbc) {
        this.bookDaoJdbc = bookDaoJdbc;
        this.authorDaoJdbc = authorDaoJdbc;
        this.genreDaoJdbc = genreDaoJdbc;
    }

   public void save(Book book) throws Exception {
        if(book == null) throw new Exception("Please select a book which need save");
        this.bookDaoJdbc.insert(book);
    }

    public void addAuthorToBook(Book book, Author author) throws Exception {
        if(book != null && author != null) {
            this.bookDaoJdbc.addAuthorToBooK(book, author);
        } else {
            throw new Exception("Please select a author which need add to book");
        }
    }

    public void update(Book book) throws Exception {
        if(book == null) throw new Exception("Please select a book which need change");
        this.bookDaoJdbc.update(book);
    }

    public List<Book> findAll() {
        return this.bookDaoJdbc.getAll();
    }

    public Book findById(long id) throws Exception {
        if(id == 0) throw new Exception("Wrong id") ;
        return this.bookDaoJdbc.getById(id);
    }

    public int getCountBooks() {
        return this.bookDaoJdbc.count();
    }

    public List<Book> getBooksByAuthor(Author author) throws Exception {
        if(author == null) throw new Exception("Author not found ");
        return bookDaoJdbc.getByAuthor(author);
    }

    public List<Book> getBooksByGenre(String genreName) throws Exception {
        if(genreName == null || genreName.length() < 1) throw new Exception("Wrong name genre");
        Genre genre = this.genreDaoJdbc.getByName(genreName);
        if(genre == null) throw new Exception("Wrong name genre");
        return bookDaoJdbc.getByGenre(genre);
    }
}
