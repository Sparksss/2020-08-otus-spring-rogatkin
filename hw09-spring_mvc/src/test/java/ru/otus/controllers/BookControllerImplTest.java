package ru.otus.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.entities.Book;
import ru.otus.entities.Genre;
import ru.otus.services.BookServiceImpl;
import ru.otus.services.GenreServiceImpl;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
 * @created 06/12 - otus-spring
 * @author Ilya Rogatkin
 */
@DisplayName("Контроллер BookController должен")
@WebMvcTest(BookControllerImpl.class)
class BookControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookServiceImpl bookService;

    @MockBean
    private GenreServiceImpl genreService;


    @DisplayName("Отдававть html страницу со списком книг")
    @Test
    void getHTMLPageWithListOfBook() throws Exception {
        given(bookService.findById(1))
                .willReturn(new Book("The Captains Daughter"));

        given(genreService.findById(1))
                .willReturn(new Genre("sci_fiction"));

        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }
}