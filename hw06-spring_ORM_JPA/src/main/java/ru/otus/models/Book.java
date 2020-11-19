package ru.otus.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToMany(targetEntity = Author.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "books_authors", joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"))
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
