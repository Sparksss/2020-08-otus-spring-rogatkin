package ru.otus.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.dao.GenreDaoJdbc;
import ru.otus.domains.Genre;

import java.util.List;


@Component
public class GenreServiceImpl implements GenreService {

    GenreDaoJdbc genreDaoJdbc;

    @Autowired
    public GenreServiceImpl(GenreDaoJdbc genreDaoJdbc) {
        this.genreDaoJdbc = genreDaoJdbc;
    }

    public List<Genre> findAll() {
        return this.genreDaoJdbc.getAll();
    }

    public Genre findById(long id) throws Exception {
        return this.getById(id);
    }

    public Genre getByName(String name) throws Exception {
        if(name == null || name.isEmpty()) throw new Exception("Please enter a name of genre");
        Genre genre = this.genreDaoJdbc.getByName(name);
        if(genre == null) throw new Exception(String.format("%s%s%s", "Genre with name",name,"does not exists"));
        return genre;
    }

    public int countAll() {
        return this.genreDaoJdbc.count();
    }

    public void save(Genre genre) throws Exception {
        if(genre == null) throw new Exception("Genre is null");
        this.genreDaoJdbc.insert(genre);
    }

    public void update(long id, String genreName) throws Exception {
        Genre genre = this.getById(id);
        genre.setName(genreName);
        this.genreDaoJdbc.update(genre);
    }

    public void delete(long id) throws Exception {
        this.genreDaoJdbc.delete(this.getById(id));
    }

    private Genre getById(long id) throws Exception {
        if(id == 0) throw new Exception("Wrong parameter id");
        Genre genre = this.genreDaoJdbc.getById(id);
        if(genre == null) throw new Exception(String.format("%s%s%s", "Genre with id",id,"does not exists"));
        return genre;
    }

}
