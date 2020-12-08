package ru.otus.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.entities.Genre;
import ru.otus.exceptions.NotFoundException;
import ru.otus.exceptions.ValidateException;
import ru.otus.repositories.GenreRepositoryJPA;

import java.util.ArrayList;
import java.util.List;

/*
 * @created 14/11 - otus-spring
 * @author Ilya Rogatkin
 */
@Service
public class GenreServiceImpl implements GenreService {

    private GenreRepositoryJPA genreRepositoryJPA;

    @Autowired
    public GenreServiceImpl(GenreRepositoryJPA genreRepositoryJPA) {
        this.genreRepositoryJPA = genreRepositoryJPA;
    }

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
        return this.genreRepositoryJPA.findById(id).get();
    }

    @Transactional(readOnly = true)
    @Override
    public Genre getByName(String name) throws ValidateException, NotFoundException {
        if(name == null || name.isEmpty()) throw new ValidateException("Wrong name genre");
        Genre genre = this.genreRepositoryJPA.findByName(name);
        if(genre == null) throw new NotFoundException("Genre with name:" + name + "Not found");
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
        Genre genre = this.genreRepositoryJPA.findById(id).get();
        if(genre == null) throw new NotFoundException("Genre with id: " + id + "not found");
        genre.setName(genreName);
        this.genreRepositoryJPA.save(genre);
    }

    @Transactional
    @Override
    public void delete(long id) throws ValidateException, NotFoundException {
        if(id == 0) throw new ValidateException("Wrong parameter genreId");
        Genre genre = this.genreRepositoryJPA.findById(id).get();
        if(genre != null) {
            this.genreRepositoryJPA.delete(genre);
        } else {
            throw new NotFoundException("Genre with id " + id + "not found");
        }
    }
}
