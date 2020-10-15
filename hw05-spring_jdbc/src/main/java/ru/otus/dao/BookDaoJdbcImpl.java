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

    public BookDaoJdbcImpl() {
    }

    @Autowired
    public BookDaoJdbcImpl(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int count() {
        return jdbc.getJdbcOperations().queryForObject("select count(*) from books", Integer.class);
    }

    @Override
    public void insert(Book book) {
        Genre genre = book.getGenre();
        List<Author> authors = book.getAuthors();
        jdbc.update("insert into books(name) values (:name)", Map.of("name", book.getName()));

        if(genre != null) {
            Map<String, Object> values = new HashMap<>();
            values.put("name", book.getName());
            values.put("genre_id", genre.getId());
            jdbc.update("insert into books(name,genre_id) values(:name,:genre_id)", values);
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
    }

    @Override
    public Book getById(long id) {
        return jdbc.query("select id as book_id, name as book_name from books where id = :id", Map.of("id",id), new BookMapper()).get(0);
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select b.id as book_id, b.name as book_name from books as b", new BookMapper());
    }

    @Override
    public List<Book> getByGenre(Genre genre) {
        return jdbc.query("select b.name as book_name, b.id as book_id, g.id as genre_id, g.name as genre_name from books as b inner join genres as g on g.id = b.genre_id where g.name = :name", Map.of("name", String.format("%s%s%s", "%", genre.getName(), "%")), new BookMapper());
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
            return new Book(id, name);
        }
    }
}
