package ru.otus.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.domains.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ilya on Oct, 2020
 */
@Repository
public class AuthorDaoJdbcImpl implements AuthorDaoJdbc {

    private NamedParameterJdbcOperations jdbc;

    public AuthorDaoJdbcImpl() {
    }

    @Autowired
    public AuthorDaoJdbcImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbc = namedParameterJdbcOperations;
    }

    @Override
    public int count() {
        return jdbc.getJdbcOperations().queryForObject("select count(*) from authors", Integer.class);
    }

    @Override
    public void insert(Author author) {
        jdbc.update("insert into authors (name) values (:name)", Map.of("name", author.getName()));
    }

    @Override
    public Author getById(long id) {
        return jdbc.query("select * from authors where id = :id", Map.of("id", id), new AuthorMapper()).get(0);
    }

    @Override
    public Author getByName(String name) {
        return jdbc.query("select * from authors where name like :name", Map.of("name", String.format("%s%s%s", "%", name, "%")), new AuthorMapper()).get(0);
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("select * from authors", new AuthorMapper());
    }

    @Override
    public void update(Author author) {
        Map<String, Object> values = new HashMap<>();
        values.put("name", author.getName());
        values.put("id", author.getId());
        jdbc.update("update authors set name = :name where id = :id", values);
    }

    @Override
    public void delete(Author author) {
        jdbc.update("delete from authors where id = :id", Map.of("id", author.getId()));
    }

    @Override
    public List<Author> getAllByBookId(long bookId) {
        return jdbc.query("select * from authors inner join books_authors ba on authors.id = ba.author_id where book_id = :book_id", Map.of("book_id", bookId), new AuthorMapper());
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Author(id, name);
        }
    }
}
