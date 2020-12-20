package ru.otus.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.entities.User;

import java.util.Optional;

/*
 * @created 13/12 - otus-spring
 * @author Ilya Rogatkin
 */
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
