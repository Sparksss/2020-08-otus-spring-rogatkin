package ru.otus.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.models.Genre;
import ru.otus.repository.GenreRepositoryJPA;

import org.springframework.transaction.annotation.Transactional;
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
        return this.genreRepositoryJPA.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Genre findById(long id) throws Exception {
        if(id == 0) throw new Exception("Wrong parameter id");
        return this.genreRepositoryJPA.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Genre getByName(String name) throws Exception {
        if(name == null || name.isEmpty()) throw new Exception("Wrong name genre");
        return this.genreRepositoryJPA.findByName(name);
    }

    @Transactional(readOnly = true)
    @Override
    public int countAll() {
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
        Genre genre = this.genreRepositoryJPA.findById(id);
        genre.setName(genreName);
        this.genreRepositoryJPA.save(genre);
    }

    @Transactional
    @Override
    public void delete(long id) throws Exception {
        if(id == 0) throw new Exception("Wrong parameter genreId");
        this.genreRepositoryJPA.delete(id);
    }
}
