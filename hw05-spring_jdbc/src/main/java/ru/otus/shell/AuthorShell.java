package ru.otus.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.services.AuthorService;

@ShellComponent
public class AuthorShell {
    private AuthorService authorService;

    public AuthorShell(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ShellMethod(value = "add new author", key = {"add_author"})
    public void add(@ShellOption String authorName) throws Exception {
        this.authorService.save(authorName);
    }

    @ShellMethod(value = "update name exists author by id", key = {"change_name"})
    public void update(@ShellOption long id, String authorName) throws Exception {
        this.authorService.update(id, authorName);
    }

    @ShellMethod(value = "delete exists author", key = {"delete"})
    public void delete(@ShellOption long id) throws Exception {
        this.authorService.delete(id);
    }

    @ShellMethod(value = "find all authors", key = {"all_authors"})
    public void all() {
        this.authorService.findAll();
    }
}
