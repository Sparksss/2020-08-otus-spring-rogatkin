package ru.otus.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.entities.Genre;
import ru.otus.services.GenreServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
 * @created 10/12 - otus-spring
 * @author Ilya Rogatkin
 */
@DisplayName("Контроллер GenreControllerImpl должен")
@WebMvcTest(GenreControllerImpl.class)
class GenreControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GenreServiceImpl genreService;

    @DisplayName("Отдавать список всех жанров")
    @Test
    void getListALLGenres() throws Exception {
        final long GENRE1_ID = 1;
        final String GENRE1_NAME = "Novel";
        final long GENRE2_ID = 2;
        final String GENRE2_NAME = "Detective";


        Genre genre1 = new Genre(GENRE1_ID, GENRE1_NAME);
        Genre genre2 = new Genre(GENRE2_ID, GENRE2_NAME);

        List<Genre> genres = Arrays.asList(genre1, genre2);

        given(genreService.findAll())
                .willReturn(genres);

        this.mockMvc.perform(get("/genre"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(GENRE1_ID))
                .andExpect(jsonPath("$[0].name").value(GENRE1_NAME))
                .andExpect(jsonPath("$[1].id").value(GENRE2_ID))
                .andExpect(jsonPath("$[1].name").value(GENRE2_NAME));
    }
}