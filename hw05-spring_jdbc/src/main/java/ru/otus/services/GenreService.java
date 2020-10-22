package ru.otus.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.dao.GenreDaoJdbc;
import ru.otus.domains.Genre;

import java.util.List;

@Component
public class GenreService {

    GenreDaoJdbc genreDaoJdbc;

    @Autowired
    public GenreService(GenreDaoJdbc genreDaoJdbc) {
        this.genreDaoJdbc = genreDaoJdbc;
    }

    public List<Genre> getAll() {
        return this.genreDaoJdbc.getAll();
    }

    public Genre getById(long id) throws Exception {
        if(id == 0) throw new Exception("Wrong id");
        return this.genreDaoJdbc.getById(id);
    }

    public Genre getByName(String name) throws Exception {
        if(name == null || name.isEmpty()) throw new Exception("Please enter a name of genre");
        return this.genreDaoJdbc.getByName(name);
    }

    public int countAll() {
        return this.genreDaoJdbc.count();
    }

    public void save(Genre genre) throws Exception {
        if(genre == null) throw new Exception("Genre is null");
        this.genreDaoJdbc.insert(genre);
    }

    public void update(Genre genre) throws Exception {
        if(genre == null) throw new Exception("Genre is null");
        this.genreDaoJdbc.update(genre);
    }


}
