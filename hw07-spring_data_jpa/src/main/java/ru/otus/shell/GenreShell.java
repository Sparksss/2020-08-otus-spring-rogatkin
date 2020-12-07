package ru.otus.shell;

/*
 * @created 07/12 - otus-spring
 * @author Ilya Rogatkin
 */
public interface GenreShell {
    void add(String genreName);
    void all();
    void findById(long id);
    void delete(long id);
    void findByName(String name);
    void countGenres();
    void changeName(long genreId, String newGenreName);
}
