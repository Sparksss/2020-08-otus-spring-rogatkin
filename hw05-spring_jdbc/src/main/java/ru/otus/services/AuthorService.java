package ru.otus.services;

import org.springframework.stereotype.Component;
import ru.otus.dao.AuthorDaoJdbc;
import ru.otus.domains.Author;

import java.util.List;

@Component
public class AuthorService {
    private AuthorDaoJdbc authorDaoJdbc;

    public AuthorService(AuthorDaoJdbc authorDaoJdbc) {
        this.authorDaoJdbc = authorDaoJdbc;
    }

    public void save(String authorName) throws Exception {
        if(authorName == null || authorName.isEmpty()) throw new Exception("Please fill author name");
        this.authorDaoJdbc.insert(new Author(authorName));
    }

    public void update(long id, String authorName) throws Exception {
        if(id == 0) throw new Exception("Wrong parameter id");
        if(authorName == null || authorName.isEmpty()) throw new Exception("Please fill author name");
        Author author = this.authorDaoJdbc.getById(id);
        if(author == null) throw new Exception("Author with id" + id + "does not exists");
        author.setName(authorName);
        this.authorDaoJdbc.update(author);
    }

    public void delete(long id) throws Exception {
        if(id == 0) throw new Exception("Wrong parameter id");
        Author author = this.authorDaoJdbc.getById(id);
        if(author == null) throw new Exception("Author with id" + id + "does not exists");
        this.authorDaoJdbc.delete(author);
    }

    public void findAll() {
        List<Author> authors = this.authorDaoJdbc.getAll();
        for(Author author : authors) {
            System.out.println(author);
        }
    }

    public void findById(long id) throws Exception {
        if(id == 0) throw new Exception("Wrong parameter id");
        Author author = this.authorDaoJdbc.getById(id);
        if(author == null) throw new Exception("Author with id" + id + "does not exists");
        System.out.println(author);
    }
}
