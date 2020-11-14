package ru.otus.services;

import org.springframework.stereotype.Component;
import ru.otus.models.Genre;
import ru.otus.repository.GenreRepositoryJPA;

import java.util.List;

/*
 * @created 14/11 - otus-spring
 * @author Ilya Rogatkin
 */
@Component
public class GenreServiceImpl implements GenreService {

    private GenreRepositoryJPA genreRepositoryJPA;

    public GenreServiceImpl(GenreRepositoryJPA genreRepositoryJPA) {
        this.genreRepositoryJPA = genreRepositoryJPA;
    }

    @Override
    public List<Genre> findAll() {
        return this.genreRepositoryJPA.findAll();
    }

    @Override
    public Genre findById(long id) throws Exception {
        if(id == 0) throw new Exception("Wrong parameter id");
        return this.genreRepositoryJPA.findById(id);
    }

    @Override
    public Genre getByName(String name) throws Exception {
        if(name == null || name.isEmpty()) throw new Exception("Wrong name genre");
        return this.genreRepositoryJPA.findByName(name);
    }

    @Override
    public int countAll() {
        return this.genreRepositoryJPA.count();
    }

    @Override
    public void save(String genreName) throws Exception {
        if(genreName == null || genreName.isEmpty()) throw new Exception("Wrong parameter genreName");
        Genre genre = new Genre(genreName);
        this.genreRepositoryJPA.save(genre);
    }

    @Override
    public void update(long id, String genreName) throws Exception {
        if(id == 0) throw new Exception("Wrong parameter genreId");
        if(genreName == null || genreName.isEmpty()) throw new Exception("Wrong parameter genreName");
        Genre genre = this.genreRepositoryJPA.findById(id);
        genre.setName(genreName);
        this.genreRepositoryJPA.save(genre);
    }

    @Override
    public void delete(long id) throws Exception {
        if(id == 0) throw new Exception("Wrong parameter genreId");
        this.genreRepositoryJPA.delete(id);
    }
}
