package ru.otus.shell;

import java.util.List;

/*
 * @created 14/11 - otus-spring
 * @author Ilya Rogatkin
 */
public interface BookShell {
    void all();
    void add(String bookName, String genreName);
    void update(long bookId, String bookName);
    void findById(long id);
    void addAuthorToBook(long authorId, long bookId);
}
