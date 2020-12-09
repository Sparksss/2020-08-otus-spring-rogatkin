package ru.otus.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.entities.Genre;
import ru.otus.repositories.GenreRepositoryJPA;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/*
 * @created 07/12 - otus-spring
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
    public Genre findById(long id) throws Exception {
        if(id == 0) throw new Exception("Wrong parameter id");
        return this.genreRepositoryJPA.findById(id).get();
    }

    @Transactional(readOnly = true)
    @Override
    public Genre getByName(String name) throws Exception {
        if(name == null || name.isEmpty()) throw new Exception("Wrong name genre");
        return this.genreRepositoryJPA.findByName(name);
    }

    @Transactional(readOnly = true)
    @Override
    public Long countAll() {
        return this.genreRepositoryJPA.count();
    }

    @Transactional
    @Override
    public void save(String genreName) throws Exception {
        if(genreName == null || genreName.isEmpty()) throw new Exception("Wrong parameter genreName");
        Genre genre = new Genre(genreName);
        this.genreRepositoryJPA.save(genre);
    }

    @Transactional
    @Override
    public void update(long id, String genreName) throws Exception {
        if(id == 0) throw new Exception("Wrong parameter genreId");
        if(genreName == null || genreName.isEmpty()) throw new Exception("Wrong parameter genreName");
        Genre genre = this.genreRepositoryJPA.findById(id).get();
        if(genre == null) throw new Exception("Genre with id: " + id + "not found");
        genre.setName(genreName);
        this.genreRepositoryJPA.save(genre);
    }

    @Transactional
    @Override
    public void delete(long id) throws Exception {
        if(id == 0) throw new Exception("Wrong parameter genreId");
        Genre genre = this.genreRepositoryJPA.findById(id).get();
        if(genre != null) {
            this.genreRepositoryJPA.delete(genre);
        } else {
            throw new Exception("Genre with id " + id + "not found");
        }
    }
}