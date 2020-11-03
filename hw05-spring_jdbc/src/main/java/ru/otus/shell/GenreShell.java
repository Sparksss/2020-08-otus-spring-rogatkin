package ru.otus.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.domains.Genre;
import ru.otus.services.GenreService;

import java.util.List;


@ShellComponent
public class GenreShell {
    private GenreService genreService;

    public GenreShell(GenreService genreService) {
        this.genreService = genreService;
    }

    @ShellMethod(value = "add new genre in dictionary", key = {"add_genre"})
    public void add(@ShellOption String genreName) throws Exception {
        this.genreService.save(new Genre(genreName));
    }

    @ShellMethod(value = "change name genre by id", key = {"change_genre"})
    public void update(@ShellOption long id, @ShellOption String genreName) throws Exception {
        this.genreService.update(id, genreName);
    }

    @ShellMethod(value = "delete genre by id", key = {"delete_genre"})
    public void delete(@ShellOption long id) throws Exception {
        this.genreService.delete(id);
    }

    @ShellMethod(value = "get a list genres in dictionary", key = {"all_genres"})
    public void all(){
        List<Genre> genres = this.genreService.findAll();
        for(Genre genre : genres) {
            System.out.println(genre);
        }
    }

    @ShellMethod(value = "get a genre by id", key = {"find_genre"})
    public void find(@ShellOption long id) throws Exception {
        Genre genre = this.genreService.findById(id);
        System.out.println(genre);
    }
}
