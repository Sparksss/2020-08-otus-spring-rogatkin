package ru.otus.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.entities.Author;
import ru.otus.exceptions.NotFoundException;
import ru.otus.exceptions.ValidateException;
import ru.otus.repositories.AuthorRepositoryJPA;

import java.util.ArrayList;
import java.util.List;

/*
 * @created 12/12 - otus-spring
 * @author Ilya Rogatkin
 */
@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepositoryJPA authorRepositoryJPA;

    @Transactional
    @Override
    public void save(String authorName) throws ValidateException {
        if(authorName == null || authorName.isEmpty()) throw new ValidateException("Author name cannon be empty");
        Author author = new Author(authorName);
        this.authorRepositoryJPA.save(author);
    }

    @Transactional
    @Override
    public void update(long id, String authorName) throws NotFoundException {
        if(id == 0) throw new ValidateException("id cannot be 0");
        if(this.isCorrectValue(authorName)) {
            Author author = this.authorRepositoryJPA.findById(id).orElseThrow(() -> {throw new NotFoundException("Author with id: " + id + "Not found");});
            author.setName(authorName);
            this.authorRepositoryJPA.save(author);
        } else {
            throw new ValidateException("Wrong author name");
        }
    }

    @Transactional
    @Override
    public void delete(long id) throws NotFoundException, ValidateException{
        if(id == 0) throw new ValidateException("Wrong id");
        this.authorRepositoryJPA.findById(id)
                .ifPresentOrElse(author -> authorRepositoryJPA.delete(author), () -> {throw new NotFoundException("Author with id" + id + "not found");});
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
    public Author findById(long id) throws ValidateException {
        if(id == 0) throw new ValidateException("Wrong id");
        return this.authorRepositoryJPA.findById(id).orElseThrow(() -> {throw new NotFoundException("Author with id: " + id + "Not found");});
    }

    private boolean isCorrectValue(String value) {
        return value != null && !value.isEmpty();
    }
}
