package ru.otus.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.models.Author;
import ru.otus.services.AuthorService;

/*
 * @created 19/11 - otus-spring
 * @author Ilya Rogatkin
 */
@ShellComponent
public class AuthorShellImpl implements AuthorShell {

    private AuthorService authorService;

    @Autowired
    public AuthorShellImpl(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ShellMethod(value = "add new author", key = {"add_author"})
    @Override
    public void add(@ShellOption String authorName) {
        try {
            this.authorService.save(authorName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
        }
    }

    @ShellMethod(value = "show book by id", key = {"find_by_id"})
    @Override
    public void findById(@ShellOption long id) {
        try {
            System.out.println(this.authorService.findById(id));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @ShellMethod(value = "delete book", key = {"delete_book"})
    @Override
    public void delete(@ShellOption long id) {
        try {
            this.authorService.delete(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
