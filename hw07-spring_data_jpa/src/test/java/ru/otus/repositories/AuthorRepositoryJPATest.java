package ru.otus.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.entities.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
/*
 * @created 07/12 - otus-spring
 * @author Ilya Rogatkin
 */
@DisplayName("Репозиторий Author Должен")
@DataJpaTest
class AuthorRepositoryJPATest {

    @Autowired
    private AuthorRepositoryJPA authorRepositoryJPA;

    private final Long INITIAL_COUNT_AUTHORS = 3L;
    private final long FIRST_ID = 1L;
    private final String PUSHKIN_NAME = "Alexander_Pushkin";


    @DisplayName("Отдавать общее количество авторов")
    @Test
    public void countAllAuthors() {
        Long expectedCount = this.authorRepositoryJPA.count();
        assertEquals(expectedCount, INITIAL_COUNT_AUTHORS);
    }


    @DisplayName("Получать список всех авторов")
    @Test
    public void getAllAuthors() {
        List<Author> authors = this.authorRepositoryJPA.findAll();
        assertThat(authors).isNotNull();
        assertEquals(authors.size(), INITIAL_COUNT_AUTHORS);
    }


    @DisplayName("Находить автора по ID")
    @Test
    public void findAuthorById() {
        Author author = this.authorRepositoryJPA.findById(FIRST_ID);
        assertThat(author).isNotNull();
    }

    @DisplayName("Находить автора по имени")
    @Test
    public void findByName() {
        Author author = this.authorRepositoryJPA.findByName(PUSHKIN_NAME);
        assertThat(author).isNotNull();
    }

    @DisplayName("Добавлять нового автора")
    @Test
    public void addNewAuthor() {
        final String NEW_AUTHOR_NAME = "Sergey_Esenin";
        Author expectedAuthor = new Author(NEW_AUTHOR_NAME);
        Author actualAuthor = this.authorRepositoryJPA.save(expectedAuthor);
        assertEquals(expectedAuthor.getName(), actualAuthor.getName());
    }
}
