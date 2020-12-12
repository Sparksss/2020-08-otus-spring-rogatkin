package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.entities.Genre;
import ru.otus.services.GenreService;

import java.util.logging.Logger;

/*
 * @created 07/12 - otus-spring
 * @author Ilya Rogatkin
 */
@RequiredArgsConstructor
@ShellComponent
public class GenreShellImpl implements GenreShell {

    private final GenreService genreService;

    private static Logger logger = Logger.getLogger("BookLogger");

    @ShellMethod(value = "add new genre", key = {"add_genre"})
    @Override
    public void add(@ShellOption String genreName) {
        try {
            this.genreService.save(genreName);
        } catch (Exception e) {
            logger.warning(e.getMessage());
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
            System.out.println(genre != null ? genre : String.format("%s%s%s", "genre with id: ", id, " does not exists"));
        } catch (Exception e) {
            logger.warning(e.getMessage());
        }
    }

    @ShellMethod(value = "delete genre", key = {"delete_genre"})
    @Override
    public void delete(@ShellOption long id) {
        try {
            this.genreService.delete(id);
        } catch (Exception e) {
            logger.warning(e.getMessage());
        }
    }

    @ShellMethod(value = "find by name", key = {"find_by_name"})
    public void findByName(@ShellOption String name) {
        try {
            Genre genre = this.genreService.getByName(name);
            System.out.println(genre);
        } catch (Exception e) {
            logger.warning(e.getMessage());
        }
    }

    @ShellMethod(value = "count all genres", key = {"count_genre"})
    public void countGenres() {
        System.out.println(this.genreService.countAll());
    }

    @ShellMethod(value = "change name genre", key = {"change_name"})
    public void changeName(@ShellOption long genreId, @ShellOption String newGenreName) {
        try {
            this.genreService.update(genreId, newGenreName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
