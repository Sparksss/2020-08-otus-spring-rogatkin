package ru.otus.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.domains.Author;
import ru.otus.services.AuthorService;

import java.util.List;

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
        List<Author> authors = this.authorService.findAll();
        for(Author author : authors) {
            System.out.println(author);
        }
    }

    @ShellMethod(value = "get author by id", key = {"find_author"})
    public void findById(long id) throws Exception {
        Author author = this.authorService.findById(id);
        System.out.println(author);
    }
}
