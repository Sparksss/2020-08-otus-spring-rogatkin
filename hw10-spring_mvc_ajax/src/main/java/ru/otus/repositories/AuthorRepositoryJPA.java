package ru.otus.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.entities.Author;

import java.util.List;

/*
 * @created 05/12 - otus-spring
 * @author Ilya Rogatkin
 */
public interface AuthorRepositoryJPA extends CrudRepository<Author, Long> {}