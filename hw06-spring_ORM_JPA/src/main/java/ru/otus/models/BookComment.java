package ru.otus.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
 * @created 26/11 - otus-spring
 * @author Ilya Rogatkin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books_comments")
public class BookComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "comment_id")
    private Long commentId;

    public BookComment(Long bookId, Long commentId) {
        this.bookId = bookId;
        this.commentId = commentId;
    }
}
