package ru.otus.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.entities.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * @created 07/12 - otus-spring
 * @author Ilya Rogatkin
 */
@DisplayName("Репозиторий Genre должен")
@DataJpaTest
class GenreRepositoryJPATest {

    @Autowired
    private GenreRepositoryJPA genreRepositoryJPA;

    private final Long FIRST_ID = 1L;
    private final String DETECTIVE = "Detective";
    private final Long INITIAL_COUNT_GENRES = 3L;


    @DisplayName("Получать общее количество жанров")
    @Test
    public void count() {
        Long count = this.genreRepositoryJPA.count();
        assertEquals(INITIAL_COUNT_GENRES, count);
    }


    @DisplayName("Получать список жанров")
    @Test
    public void findAll() {
        List<Genre> genres = this.genreRepositoryJPA.findAll();
        assertThat(genres).isNotNull();
        assertEquals(genres.size(), INITIAL_COUNT_GENRES);
    }

    @DisplayName("Добавлять новый жанр")
    @Test
    public void addNewGenre() {
        Genre expectedGenre = new Genre("Comedy");
        Genre actualGenre = this.genreRepositoryJPA.save(expectedGenre);
        assertEquals(expectedGenre.getName(), actualGenre.getName());
    }

    @DisplayName("Изменять имя жанра")
    @Test
    public void updateGenreNameById() {
        final String NEW_NAME_GENRE = "Thriller";
        Genre expectedGenre = this.genreRepositoryJPA.findById(FIRST_ID).get();
        expectedGenre.setName(NEW_NAME_GENRE);
        Genre actualGenre = this.genreRepositoryJPA.save(expectedGenre);
        assertEquals(expectedGenre.getName(), actualGenre.getName());
    }

    @DisplayName("Получать жанр по ID")
    @Test
    public void findById() {
        Genre genre = this.genreRepositoryJPA.findById(FIRST_ID).get();
        assertThat(genre).isNotNull();
    }

    @DisplayName("Находить жанр по имени")
    @Test
    public void findByName() {
        Genre genre = this.genreRepositoryJPA.findByName(DETECTIVE);
        assertThat(genre).isNotNull().hasFieldOrProperty("name");
    }

}
