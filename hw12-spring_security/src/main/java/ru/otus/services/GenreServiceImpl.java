package ru.otus.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.entities.Genre;
import ru.otus.exceptions.NotFoundException;
import ru.otus.exceptions.ValidateException;
import ru.otus.repositories.GenreRepositoryJPA;

import java.util.ArrayList;
import java.util.List;

/*
 * @created 13/12 - otus-spring
 * @author Ilya Rogatkin
 */
@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepositoryJPA genreRepositoryJPA;

    @Transactional(readOnly = true)
    @Override
    public List<Genre> findAll() {
        List<Genre> genres = new ArrayList<>();
        this.genreRepositoryJPA.findAll().forEach(genres::add);
        return genres;
    }

    @Transactional(readOnly = true)
    @Override
    public Genre findById(long id) throws ValidateException {
        if(id == 0) throw new ValidateException("Wrong parameter id");
        return this.genreRepositoryJPA.findById(id).orElseThrow(() -> {throw new NotFoundException("Genre with id: " + id + "not found");});
    }

    @Transactional(readOnly = true)
    @Override
    public Genre getByName(String name) throws ValidateException, NotFoundException {
        if(name == null || name.isEmpty()) throw new ValidateException("Wrong name genre");
        Genre genre = this.genreRepositoryJPA.findByName(name)
                .orElseThrow(() -> {throw new NotFoundException("Genre with name:" + name + "Not found");});
        return genre;
    }

    @Transactional(readOnly = true)
    @Override
    public Long countAll() {
        return this.genreRepositoryJPA.count();
    }

    @Transactional
    @Override
    public void save(String genreName) throws ValidateException {
        if(genreName == null || genreName.isEmpty()) throw new ValidateException("Wrong parameter genreName");
        Genre genre = new Genre(genreName);
        this.genreRepositoryJPA.save(genre);
    }

    @Transactional
    @Override
    public void update(long id, String genreName) throws ValidateException, NotFoundException {
        if(id == 0) throw new ValidateException("Wrong parameter genreId");
        if(genreName == null || genreName.isEmpty()) throw new ValidateException("Wrong parameter genreName");
        Genre genre = this.genreRepositoryJPA.findById(id)
                .orElseThrow(() -> {throw new NotFoundException("Genre with id: " + id + "not found");});
        genre.setName(genreName);
        this.genreRepositoryJPA.save(genre);
    }

    @Transactional
    @Override
    public void delete(long id) throws ValidateException, NotFoundException {
        if(id == 0) throw new ValidateException("Wrong parameter genreId");
        this.genreRepositoryJPA.findById(id)
                .ifPresentOrElse(genre -> { genreRepositoryJPA.delete(genre); }, () -> {throw new NotFoundException("Genre with id " + id + "not found");});
    }
}
