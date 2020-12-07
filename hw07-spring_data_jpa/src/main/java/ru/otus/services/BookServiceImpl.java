package ru.otus.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.entities.*;
import ru.otus.repositories.*;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * @created 07/12 - otus-spring
 * @author Ilya Rogatkin
 */
@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepositoryJPA bookRepository;
    private final GenreRepositoryJPA genreRepositoryJPA;
    private final AuthorRepositoryJPA authorRepositoryJPA;
    private final CommentRepositoryJPA commentRepositoryJPA;

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
        List<Book> books = this.bookRepository.findAll();
        for(Book book: books) {
            book.getComments().size();
            book.getAuthors().size();
        }
        return books;
    }

    @Transactional(readOnly = true)
    @Override
    public Book findById(long id) throws Exception {
        if(id == 0) throw new Exception("Wrong parameter id");
        Book book = this.bookRepository.findById(id);
        if(book == null) throw new Exception(String.format("%s%d%s", "Book with id: ", id, "does not exists"));
        List<Comment> comments = book.getComments();
        comments.size();
        Set<Author> authors = book.getAuthors();
        authors.size();
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

    @Transactional
    @Override
    public void addCommentToBook(String commentText, long bookId) throws Exception {
        if(!this.isCorrectValue(commentText)) throw new Exception("Please fill a comment");
        if(bookId == 0) throw new Exception("Wrong book id");
        Book book = this.bookRepository.findById(bookId);
        if(book == null) throw new Exception("Book with id: " + bookId + "not found");
        Comment comment = new Comment(commentText);
        comment.setBook(book);
        comment = this.commentRepositoryJPA.save(comment);
        if(book.getComments() == null || book.getComments().isEmpty()) {
            List<Comment> comments = new ArrayList<>();
            comments.add(comment);
            book.setComments(comments);
        } else {
            book.getComments().add(comment);
        }
    }

    private boolean isCorrectValue(String value) {
        return value != null && !value.isEmpty();
    }
}