package ru.otus.shell;

/*
 * @created 19/11 - otus-spring
 * @author Ilya Rogatkin
 */
public interface AuthorShell {
    void add(String authorName);
    void all();
    void findById(long id);
    void delete(long id);
}
