package ru.otus.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.entities.Genre;
import ru.otus.repositories.GenreRepositoryJPA;

import java.util.List;

/*
 * @created 07/12 - otus-spring
 * @author Ilya Rogatkin
 */
@RequiredArgsConstructor
@RestController
public class GenreControllerImpl {
    private final GenreRepositoryJPA genreRepositoryJPA;

    @GetMapping(value = "/genre")
    public List<Genre> getGenres() {
        return this.genreRepositoryJPA.findAll();
    }
}
