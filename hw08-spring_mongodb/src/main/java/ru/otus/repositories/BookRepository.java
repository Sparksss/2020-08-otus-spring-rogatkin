package ru.otus.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.entity.Book;


/*
 * @created 16/12 - otus-spring
 * @author Ilya Rogatkin
 */
public interface BookRepository extends CrudRepository<Book, String> {
    Book findByName(String name);
}
