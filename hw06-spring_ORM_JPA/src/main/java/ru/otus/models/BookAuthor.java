package ru.otus.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
 * @created 16/11 - otus-spring
 * @author Ilya Rogatkin
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "books_authors")
public class BookAuthor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "book_id")
    private long bookId;

    @Column(name = "author_id")
    private long authorId;

    public BookAuthor(long bookId, long authorId) {
        this.bookId = bookId;
        this.authorId = authorId;
    }
}
