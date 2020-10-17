package ru.otus.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.domains.Author;
import ru.otus.domains.Book;
import ru.otus.domains.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ilya on Oct, 2020
 */
@Repository
public class BookDaoJdbcImpl implements BookDaoJdbc {

    private NamedParameterJdbcOperations jdbc;

    @Autowired
    public BookDaoJdbcImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbc = namedParameterJdbcOperations;
    }

    @Override
    public int count() {
        return jdbc.getJdbcOperations().queryForObject("select count(*) from books", Integer.class);
    }

    @Override
    public Book insert(Book book) {
        long id = 0;
        Genre genre = book.getGenre();
        List<Author> authors = book.getAuthors();

        if(genre == null & authors == null) {
            id = jdbc.queryForObject("insert into books(name) values (:name) RETURNING books.id", Map.of("name", book.getName()), Long.class);
        } else if(genre != null) {
            Map<String, Object> values = new HashMap<>();
            values.put("name", book.getName());
            values.put("genre_id", genre.getId());
            id = jdbc.queryForObject("insert into books(name, genre_id) values(:name, :genre_id) RETURNING books.id", values, Long.class);
        }

        if(authors != null) {
            long bookId = book.getId();
            Map<String, Long> authorIds = new HashMap<>();
            authorIds.put("book_id", bookId);

            for(Author author :authors) {
                authorIds.put("author_id", author.getId());
                jdbc.update("insert into books_authors(book_id, author_id) values(:book_id, :author_id)", authorIds);
            }
        }
        book.setId(id);
        return book;
    }

    @Override
    public void update(Book book) {
        jdbc.update("update books set name = :name, genre_id = :genreId", Map.of("name", book.getName(), "genre_id", book.getGenre().getId()));
    }

    @Override
    public void delete(Book book) {
        jdbc.update("delete from books where id = :id", Map.of("id", book.getId()));
    }

    @Override
    public Book getById(long id) {
        return jdbc.query("select b.id as book_id, b.name as book_name, g.id as genre_id, g.name as genre_name from books as b inner join genres g on g.id = b.genre_id where id = :id", Map.of("id",id), new BookMapper()).get(0);
    }

    @Override
    public Book getByName(String name) {
        return jdbc.query("select * from books where name = :name", Map.of("name", String.format("%s%s%s", "%", name, "%")), new BookMapper()).get(0);
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select b.id as book_id, b.name as book_name from books as b", new BookMapper());
    }

    @Override
    public List<Book> getByGenre(Genre genre) {
        return jdbc.query("select b.name as book_name, b.id as book_id, g.id as genre_id, g.name as genre_name from books as b inner join genres as g on g.id = b.genre_id where g.id = :id", Map.of("id", genre.getId()), new BookMapper());
    }

    @Override
    public List<Book> getByAuthor(Author author) {
        return jdbc.query("select b.id as book_id, b.name as book_name from books as b inner join books_authors as ba on b.id = ba.book_id inner join authors a on a.id = ba.author_id where a.name = :name", Map.of("name", String.format("%s%s%s", "%", author.getName(), "%")), new BookMapper());
    }


    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("book_id");
            String name = resultSet.getString("book_name");
            long genreId = resultSet.getLong("genre_id");
            String genreName = resultSet.getString("genre_name");
            return new Book(id, name, new Genre(genreId, genreName));
        }
    }
}
