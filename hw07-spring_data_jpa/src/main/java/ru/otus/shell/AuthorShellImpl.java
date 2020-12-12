package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.entities.Author;
import ru.otus.services.AuthorService;

import java.util.logging.Logger;

/*
 * @created 07/12 - otus-spring
 * @author Ilya Rogatkin
 */
@RequiredArgsConstructor
@ShellComponent
public class AuthorShellImpl implements AuthorShell {

    private final AuthorService authorService;

    private static Logger logger = Logger.getLogger("BookLogger");

    @ShellMethod(value = "add new author", key = {"add_author"})
    @Override
    public void add(@ShellOption String authorName) {
        try {
            this.authorService.save(authorName);
        } catch (Exception e) {
            logger.warning(e.getMessage());
        }
    }

    @ShellMethod(value = "show all authors", key = {"all_authors"})
    @Override
    public void all() {
        try {
            for(Author author : this.authorService.findAll()) {
                System.out.println(author);
            }
        } catch (Exception e) {
            logger.warning(e.getMessage());
        }
    }

    @ShellMethod(value = "show book by id", key = {"find_by_id"})
    @Override
    public void findById(@ShellOption long id) {
        try {
            System.out.println(this.authorService.findById(id));
        } catch (Exception e) {
            logger.warning(e.getMessage());
        }
    }

    @ShellMethod(value = "delete book", key = {"delete_book"})
    @Override
    public void delete(@ShellOption long id) {
        try {
            this.authorService.delete(id);
        } catch (Exception e) {
            logger.warning(e.getMessage());
        }
    }

    @ShellMethod(value = "change name author", key = {"change_author_name"})
    @Override
    public void changeName(@ShellOption long authorId, @ShellOption String newAuthorName) {
        try {
            this.authorService.update(authorId, newAuthorName);
        } catch (Exception e) {
            logger.warning(e.getMessage());
        }
    }

    @ShellMethod(value = "find author by name", key = {"find_by_author_name"})
    @Override
    public void findByName(@ShellOption String authorName) {
        try {
            System.out.println(this.authorService.findByName(authorName));
        } catch (Exception e) {
            logger.warning(e.getMessage());
        }
    }
}
