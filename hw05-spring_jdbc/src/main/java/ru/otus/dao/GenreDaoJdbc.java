package ru.otus.dao;null

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import ru.otus.domains.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by ilya on Oct, 2020
 */
public class GenreDaoJdbc implements GenreDao {

    private NamedParameterJdbcOperations jdbc;

    public GenreDaoJdbc() {
    }

    @Autowired
    public GenreDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }


    @Override
    public int count() {
        return jdbc.getJdbcOperations().queryForObject("select count(*) from genres", Integer.class);
    }

    @Override
    public Genre getById(short id) {
        return jdbc.queryForObject("select * from genres where id = :id", Map.of("id", id), new GenreMapper());
    }

    @Override
    public void insert(Genre genre) {
        jdbc.update("insert into genres(name) values (:name)", Map.of("name", genre.getName()));
    }

    @Override
    public void update(Genre genre) {
        jdbc.update("update genres set name = :name", Map.of("name", genre.getName()));
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("select * from genres", new GenreMapper());
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            short id = resultSet.getShort("id");
            String name = resultSet.getString("name");
            return new Genre(id, name);
        }
    }
}
