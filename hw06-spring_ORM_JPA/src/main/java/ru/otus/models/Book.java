package ru.otus.models;

import java.util.List;

/**
 * Created by ilya on Oct, 2020
 */
public class Book {
    private long id;
    private String name;
    private Genre genre;
    private List<Author> authors;

    public Book() {
    }

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

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Genre getGenre() {
        return genre;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
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
