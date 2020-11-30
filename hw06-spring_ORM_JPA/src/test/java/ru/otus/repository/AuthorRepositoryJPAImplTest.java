package ru.otus.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.models.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/*
 * @created 25/11 - otus-spring
 * @author Ilya Rogatkin
 */
@DisplayName("Репозиторий Author Должен")
@DataJpaTest
@Import(AuthorRepositoryJPAImpl.class)
class AuthorRepositoryJPAImplTest {

    @Autowired
    private AuthorRepositoryJPAImpl authorRepositoryJPA;

    private final Long INITIAL_COUNT_AUTHORS = 2L;
    private final long FIRST_ID = 1L;
    private final String PUSHKIN_NAME = "Alexander_Pushkin";


    @DisplayName("Отдавать общее количество авторов")
    @Test
    public void countAllAuthors() {
        Long expectedCount = this.authorRepositoryJPA.countAll();
        assertEquals(expectedCount, INITIAL_COUNT_AUTHORS);
    }

    @DisplayName("Добавлять нового автора")
    @Test
    public void addNewAuthor() {
        final String NEW_AUTHOR_NAME = "Sergey_Esenin";
        Author expectedAuthor = new Author(NEW_AUTHOR_NAME);
        Author actualAuthor = this.authorRepositoryJPA.save(expectedAuthor);
        assertEquals(expectedAuthor.getName(), actualAuthor.getName());
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

    @DisplayName("Получать список всех авторов")
    @Test
    public void getAllAuthors() {
        List<Author> authors = this.authorRepositoryJPA.getAll();
        assertThat(authors).isNotNull();
        assertEquals(authors.size(), INITIAL_COUNT_AUTHORS);
    }
}