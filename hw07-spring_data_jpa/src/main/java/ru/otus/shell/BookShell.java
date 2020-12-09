package ru.otus.shell;

/*
 * @created 07/12 - otus-spring
 * @author Ilya Rogatkin
 */
public interface BookShell {
    void all();
    void add(String bookName, String genreName);
    void update(long bookId, String bookName);
    void findById(long id);
    void addAuthorToBook(long authorId, long bookId);
    void addCommentToBook(String commentText, long bookId);
    void countAllBooks();
}
