package ru.otus.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/*
 * @created 16/12 - otus-spring
 * @author Ilya Rogatkin
 */
@Data
@Builder
@Document("books")
public class Book {
    @Id
    @Field(name = "book_id")
    private String bookId;

    @Field(name = "name")
    private String name;


    @Field(name = "genre")
    private String genre;

    @Field(name = "authors")
    List<Author> authors;

    @Field(name = "comments")
    List<Comment> comments;

}
