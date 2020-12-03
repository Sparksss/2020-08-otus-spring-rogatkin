package ru.otus.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.models.Author;
import ru.otus.repository.AuthorRepositoryJPA;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/*
 * @created 19/11 - otus-spring
 * @author Ilya Rogatkin
 */
@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepositoryJPA authorRepositoryJPA;

    @Autowired
    public AuthorServiceImpl(AuthorRepositoryJPA authorRepositoryJPA) {
        this.authorRepositoryJPA = authorRepositoryJPA;
    }

    @Transactional
    @Override
    public void save(String authorName) throws Exception {
        if(authorName == null || authorName.isEmpty()) throw new Exception("Author name cannon be empty");
        Author author = new Author(authorName);
        this.authorRepositoryJPA.save(author);
    }

    @Transactional
    @Override
    public void update(long id, String authorName) throws Exception {
        if(id == 0) throw new Exception("id cannot be 0");
        if(this.isCorrectValue(authorName)) {
            Author author = this.authorRepositoryJPA.findById(id);
            author.setName(authorName);
            this.authorRepositoryJPA.save(author);
        } else {
            throw new Exception("Wrong author name");
        }
    }

    @Transactional
    @Override
    public void delete(long id) throws Exception {
        if(id == 0) throw new Exception("Wrong id");
        Author author = this.authorRepositoryJPA.findById(id);
        if (author != null) {
            this.authorRepositoryJPA.delete(author);
        } else {
            throw new Exception("Author with id" + id + "not found");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Author> findAll() {
        return this.authorRepositoryJPA.getAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Author findById(long id) throws Exception {
        if(id == 0) throw new Exception("Wrong id");
        return this.authorRepositoryJPA.findById(id);
    }

    private boolean isCorrectValue(String value) {
        return value != null && !value.isEmpty();
    }
}
