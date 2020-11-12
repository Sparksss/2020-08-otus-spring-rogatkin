package ru.otus.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by ilya on Oct, 2020
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "books")
public class Book {

    @Id
    private long id;
    private String name;
    private Genre genre;
    private List<Author> authors;

    public Book(String name) {
        this.name = name;
    }

    public Book(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Book(long id, String name, Genre genre) {
        this.id = id;
        this.name = name;
        this.genre = genre;
    }


    public String getAuthorsAsString() {
        StringBuilder authors = new StringBuilder();
        for(Author author : this.authors) {
            authors.append(author.getName() + " ,");
        }
        return authors.toString();
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\n' +
                ", genre=" + (genre != null ? genre.getName() : "") + '\n' +
                ", authors=" + (authors != null ? this.getAuthorsAsString() : "")  +
                '}';
    }
}
