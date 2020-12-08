package ru.otus.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.entities.Genre;
import ru.otus.services.GenreService;

import java.util.List;

/*
 * @created 07/12 - otus-spring
 * @author Ilya Rogatkin
 */
@RequiredArgsConstructor
@RestController
public class GenreControllerImpl {

    private final GenreService genreService;

    @GetMapping(value = "/genre")
    public List<Genre> getGenres() {
        return this.genreService.findAll();
    }
}
