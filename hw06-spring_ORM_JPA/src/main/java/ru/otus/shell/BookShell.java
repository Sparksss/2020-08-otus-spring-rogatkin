package ru.otus.shell;

/*
 * @created 14/11 - otus-spring
 * @author Ilya Rogatkin
 */
public interface BookShell {
    void all();
    void add(String bookName, String genreName);
    void update(long bookId, String bookName);
    void findById(long id);
    void findByAuthor(long authorId);
    void findByGenre(long genreId);
    void addAuthorToBook(long bookId, long authorId);
    void addCommentToBook(long bookId, String commentText);
}
