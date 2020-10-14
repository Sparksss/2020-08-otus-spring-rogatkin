package ru.otus.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.domains.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
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
    public AuthorDaoJdbcImpl(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
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

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Author(id, name);
        }
    }
}
