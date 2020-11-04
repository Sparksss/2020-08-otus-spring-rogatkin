package ru.otus.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.domains.Genre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by ilya on Oct, 2020
 */
@JdbcTest
@Import({GenreDaoJdbcImpl.class})
@DisplayName("GenreDaoJdbc")
class GenreDaoJdbcImplTest {

    @Autowired
    GenreDaoJdbcImpl jdbc;

    private static final int INITIAL_COUNT_GENRES = 2;
    private static final long ROMANCE_ID = 1;
    private static final String GENRE_NAME = "Romance";

    @Test
    @DisplayName("Проверяет количество начальное количество записей в БД")
    void count() {
        assertEquals(jdbc.count(), INITIAL_COUNT_GENRES);
    }

    @Test
    @DisplayName("Проверяет выбор строки по ID")
    void getById() {
        Genre genre = jdbc.getById(ROMANCE_ID);
        assertThat(genre).isNotNull().hasFieldOrPropertyWithValue("id", ROMANCE_ID);
    }

    @Test
    @DisplayName("Добавляет новый жанр")
    void addNewGenre() {
        int countRowsBeforeInsert = jdbc.count();
        Genre genre = new Genre();
        genre.setName("Fantasy");
        jdbc.insert(genre);
        assertEquals(countRowsBeforeInsert + 1, jdbc.count());
    }

    @Test
    @DisplayName("Меняет название жанра")
    void changeNameOfGenre() {
        Genre genre = this.jdbc.getById(ROMANCE_ID);
        genre.setName("Sci-fi");
        this.jdbc.update(genre);
        Genre genre1 = this.jdbc.getById(ROMANCE_ID);
        assertEquals(genre.getName(), genre1.getName());
    }

    @Test
    @DisplayName("Получает список всех жанров и сравнивает с количеством")
    void getAll() {
        assertEquals(jdbc.getAll().size(), jdbc.count());
    }

    @Test
    @DisplayName("Получает жанр по названию")
    void getByName() {
        Genre genre = jdbc.getByName(GENRE_NAME);
        assertThat(genre).isNotNull().hasFieldOrPropertyWithValue("name", GENRE_NAME);
    }
}