package ru.otus.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.domains.Author;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by ilya on Oct, 2020
 */
@JdbcTest
@Import({AuthorDaoJdbcImpl.class})
@DisplayName("AuthorDaoJdbc")
class AuthorDaoJdbcImplTest {

    @Autowired
    private AuthorDaoJdbcImpl jdbc;

    private static final int INITIAL_COUNT_WRITERS = 2;
    private static final long CHEKHOV_ID = 1;
    private static final String CHEKHOV = "Chekhov";


    @Test
    void count() {
        assertEquals(INITIAL_COUNT_WRITERS, jdbc.count());
    }

    @Test
    void insert() {
        Author author = new Author();
        author.setName("new author");
        jdbc.insert(author);
        assertEquals(INITIAL_COUNT_WRITERS + 1, jdbc.count());
    }

    @Test
    void getById() {
        Author author = jdbc.getById(CHEKHOV_ID);
        assertThat(author).isNotNull();
    }

    @Test
    void getAll() {
        assertEquals(jdbc.count(), jdbc.getAll().size());
    }

    @Test
    void getByName() {
        Author author = jdbc.getByName(CHEKHOV);
        assertThat(author).isNotNull().hasFieldOrPropertyWithValue("id", CHEKHOV_ID);
    }
}