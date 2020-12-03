package ru.otus.shell;

/*
 * @created 20/11 - otus-spring
 * @author Ilya Rogatkin
 */
public interface GenreShell {
    void add(String genreName);
    void all();
    void findById(long id);
    void delete(long id);
}
