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


    @ShellMethod(value = "get a list genres in dictionary", key = {"all_genres"})
    public void all(){
        List<Genre> genres = this.genreService.getAll();
        for(Genre genre : genres) {
            System.out.println(genre.toString());
        }
    }
}
