package ru.otus.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.models.Genre;
import ru.otus.services.GenreService;

/*
 * @created 20/11 - otus-spring
 * @author Ilya Rogatkin
 */
@ShellComponent
public class GenreShellImpl implements GenreShell {

    private GenreService genreService;

    @Autowired
    public GenreShellImpl(GenreService genreService) {
        this.genreService = genreService;
    }

    @ShellMethod(value = "add new genre", key = {"add_genre"})
    @Override
    public void add(@ShellOption String genreName) {
        try {
            this.genreService.save(genreName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @ShellMethod(value = "find all genres", key = {"all_genres"})
    @Override
    public void all() {
        for(Genre genre : this.genreService.findAll()) {
            System.out.println(genre);
        }
    }

    @ShellMethod(value = "find genre by id", key = {"find_genre_by_id"})
    @Override
    public void findById(@ShellOption long id) {
        try {
            Genre genre = this.genreService.findById(id);
            System.out.println(genre);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @ShellMethod(value = "delete genre", key = {"delete_genre"})
    @Override
    public void delete(@ShellOption long id) {
        try {
            this.genreService.delete(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
