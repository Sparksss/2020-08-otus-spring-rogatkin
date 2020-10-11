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
import java.util.List;
import java.util.Map;

/**
 * Created by ilya on Oct, 2020
 */
@Repository
public class BookDaoJdbc implements BookDao {

    private NamedParameterJdbcOperations jdbc;

    public BookDaoJdbc() {
    }

    @Autowired
    public BookDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int count() {
        return jdbc.getJdbcOperations().queryForObject("select count(*) from books", Integer.class);
    }

    @Override
    public Book insert(Book book) {
        return (Book) jdbc.query("insert into books(name, genre_id) values (:name, :genre_id)", Map.of("name", book.getName()), new BookMapper());
    }

    @Override
    public Book getById(long id) {
        return jdbc.queryForObject("select * from books where id = :id", Map.of("id",id), new BookMapper());
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select * from books", new BookMapper());
    }

    @Override
    public List<Book> getByGenre(Genre genre) {
        return jdbc.query("select * from books as b inner join genres as g on g.id = b.genre_id where g.name = :name", Map.of("name", genre.getName()),new BookMapper());
    }

    @Override
    public List<Book> getByAuthor(Author author) {
        return jdbc.query("select * from books as b inner join authors as a on a.id = b.authors where a.name = :name", Map.of("name", author.getName()),new BookMapper());
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Book(id, name);
        }
    }
}
