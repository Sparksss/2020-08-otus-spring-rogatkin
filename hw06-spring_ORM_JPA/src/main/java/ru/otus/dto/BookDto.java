package ru.otus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.models.Author;
import ru.otus.models.Genre;

import java.util.ArrayList;
import java.util.List;

/*
 * @created 23/11 - otus-spring
 * @author Ilya Rogatkin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private long bookId;
    private String name;
    private Genre genre;
    private List<Author> authors = new ArrayList<>();
}
