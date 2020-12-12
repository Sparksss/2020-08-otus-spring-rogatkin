package ru.otus.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.entities.Author;
import ru.otus.repositories.AuthorRepositoryJPA;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/*
 * @created 07/12 - otus-spring
 * @author Ilya Rogatkin
 */
@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepositoryJPA authorRepositoryJPA;

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
            Author author = this.authorRepositoryJPA.findById(id).get();
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
        Author author = this.authorRepositoryJPA.findById(id).get();
        if (author != null) {
            this.authorRepositoryJPA.delete(author);
        } else {
            throw new Exception("Author with id" + id + "not found");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Author> findAll() {
        List<Author> authors = new ArrayList<>();
        this.authorRepositoryJPA.findAll().forEach(authors::add);
        return authors;
    }

    @Transactional(readOnly = true)
    @Override
    public Author findById(long id) throws Exception {
        if(id == 0) throw new Exception("Wrong id");
        return this.authorRepositoryJPA.findById(id).get();
    }

    @Transactional(readOnly = true)
    @Override
    public Author findByName(String name) throws Exception {
        if(!this.isCorrectValue(name)) throw new Exception("Wrong name genre");
        Author author = this.authorRepositoryJPA.findByName(name);
        if(author == null) throw new Exception("Author with name: " + name + "not found");
        return author;
    }



    private boolean isCorrectValue(String value) {
        return value != null && !value.isEmpty();
    }
}