package ru.otus.shell;

/*
 * @created 07/12 - otus-spring
 * @author Ilya Rogatkin
 */
public interface AuthorShell {
    void add(String authorName);
    void all();
    void findById(long id);
    void delete(long id);
    void changeName(long authorId, String newAuthorName);
    void findByName(String authorName);
}
