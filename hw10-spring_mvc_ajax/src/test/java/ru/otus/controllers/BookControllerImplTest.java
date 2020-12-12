package ru.otus.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import ru.otus.entities.Book;
import ru.otus.entities.Genre;
import ru.otus.services.BookServiceImpl;


import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/*
 * @created 08/12 - otus-spring
 * @author Ilya Rogatkin
 */
@DisplayName("Контроллер BookControllerImplTest должен")
@WebMvcTest(BookControllerImpl.class)
class BookControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookServiceImpl bookService;


    @DisplayName("Отдавать список всех книг")
    @Test
    public void getAllBooks() throws Exception {
        final String BOOK1_NAME = "The Count of Monte Cristo";
        final String BOOK2_NAME = "Spark of Life";
        final long GENRE1_ID = 1;
        final String GENRE1_NAME = "Novel";
        final long GENRE2_ID = 2;
        final String GENRE2_NAME = "Detective";


        Book book1 = new Book(BOOK1_NAME);
        Genre genre1 = new Genre(GENRE1_ID, GENRE1_NAME);
        book1.setGenre(genre1);

        Book book2 = new Book(BOOK2_NAME);
        Genre genre2 = new Genre(GENRE2_ID, GENRE2_NAME);
        book2.setGenre(genre2);

        List<Book> books = Arrays.asList(book1, book2);

        given(bookService.findAll())
                .willReturn(books);

        this.mockMvc.perform(get("/book"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value(BOOK1_NAME))
                .andExpect(jsonPath("$[0].genre.id").value(GENRE1_ID))
                .andExpect(jsonPath("$[0].genre.name").value(GENRE1_NAME));
    }


    @DisplayName("Успешно находить книгу по ID")
    @Test
    public void findBookById() throws Exception {
        final String BOOK_NAME = "Simple Novel";
        final long BOOK_ID = 1;

        final long GENRE_ID = 1;
        final String GENRE_NAME = "Novel";


        Book book = new Book(BOOK_ID, BOOK_NAME);
        Genre genre = new Genre(GENRE_ID, GENRE_NAME);
        book.setGenre(genre);

        given(this.bookService.findById(BOOK_ID)).willReturn(book);

        this.mockMvc.perform(get("/book/1"))
                .andExpect(jsonPath("$.name").value(BOOK_NAME))
                .andExpect(jsonPath("$.genre.id").value(GENRE_ID))
                .andExpect(jsonPath("$.genre.name").value(GENRE_NAME));

    }

    @DisplayName("Добавлять новю книгу")
    @Test
    public void addNewBookInTable() throws Exception {
        Book book = new Book("New Name Book");
        Genre genre = new Genre("Novel");
        genre.setId(1);
        book.setGenre(genre);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writer().with(SerializationFeature.WRAP_ROOT_VALUE);

        given(this.bookService.addBook(book)).willReturn(book);

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = post("/book");
        mockHttpServletRequestBuilder.contentType("application/json");
        mockHttpServletRequestBuilder.content(objectMapper.writeValueAsString(book));

        this.mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("New Name Book"));
    }

}