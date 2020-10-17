package ru.otus.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
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
public class GenreDaoJdbcImpl implements GenreDaoJdbc {

    private NamedParameterJdbcOperations jdbc;

    public GenreDaoJdbcImpl() {
    }

    @Autowired
    public GenreDaoJdbcImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbc = namedParameterJdbcOperations;
    }


    @Override
    public int count() {
        return jdbc.getJdbcOperations().queryForObject("select count(*) from genres", Integer.class);
    }

    @Override
    public Genre getById(long id) {
        return jdbc.queryForObject("select * from genres where id = :id", Map.of("id", id), new GenreMapper());
    }

    @Override
    public void insert(Genre genre) {
        jdbc.update("insert into genres(name) values (:name)", Map.of("name", genre.getName()));
    }

    @Override
    public void update(Genre genre) {
        HashMap<String, Object> values = new HashMap<>();
        values.put("name", genre.getName());
        values.put("id", genre.getId());
        jdbc.update("update genres set name = :name where id = :id", values);
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("select * from genres", new GenreMapper());
    }

    @Override
    public Genre getByName(String name) {
        return jdbc.query("select * from genres where name like :name", Map.of("name", String.format("%s%s%s", "%", name, "%")), new GenreMapper()).get(0);
    }

    @Override
    public void delete(Genre genre) {
        jdbc.update("delete from genres where id = :id", Map.of("id", genre.getId()));
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
