package ru.otus.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.entities.Book;

import java.util.List;

/*
 * @created 07/12 - otus-spring
 * @author Ilya Rogatkin
 */
public interface BookRepositoryJPA extends CrudRepository<Book, Long> {
    Book findByName(String name);
}
