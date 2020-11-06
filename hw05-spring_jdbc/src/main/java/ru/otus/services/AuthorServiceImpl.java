package ru.otus.services;

import org.springframework.stereotype.Component;
import ru.otus.dao.AuthorDaoJdbc;
import ru.otus.domains.Author;

import java.util.List;


@Component
public class AuthorServiceImpl implements AuthorService {
    private AuthorDaoJdbc authorDaoJdbc;

    public AuthorServiceImpl(AuthorDaoJdbc authorDaoJdbc) {
        this.authorDaoJdbc = authorDaoJdbc;
    }

    public void save(String authorName) throws Exception {
        if(authorName == null || authorName.isEmpty()) throw new Exception("Please fill author name");
        this.authorDaoJdbc.insert(new Author(authorName));
    }

    public void update(long id, String authorName) throws Exception {
        Author author = this.getById(id);
        author.setName(authorName);
        this.authorDaoJdbc.update(author);
    }

    public void delete(long id) throws Exception {
        this.authorDaoJdbc.delete(this.getById(id));
    }

    public List<Author> findAll() {
        return this.authorDaoJdbc.getAll();
    }

    public Author findById(long id) throws Exception {
        if(id == 0) throw new Exception("Wrong parameter id");
        return this.getById(id);
    }

    private Author getById(long id) throws Exception {
        if(id == 0) throw new Exception("Wrong parameter id");
        Author author = this.authorDaoJdbc.getById(id);
        if(author == null) throw new Exception("Author with id" + id + "does not exists");
        return author;
    }
}
