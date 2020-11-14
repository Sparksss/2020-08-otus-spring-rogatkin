package ru.otus.repository;

import org.springframework.stereotype.Repository;
import ru.otus.models.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/*
 * @created 14/11 - otus-spring
 * @author Ilya Rogatkin
 */
@Repository
public class GenreRepositoryJPAImpl implements GenreRepositoryJPA {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Genre save(Genre genre) {
        if(genre.getId() == 0) {
            entityManager.persist(genre);
            return genre;
        } else {
            return entityManager.merge(genre);
        }
    }

    @Override
    public void updateById(String name, long id) {
        Query query = entityManager.createQuery("update Genre g set g.name = :name where g.id = :id");
        query.setParameter("id", id);
        query.setParameter("name", name);
        query.executeUpdate();
    }

    @Override
    public void delete(long id) {
        Query query = entityManager.createQuery("delete from Genre g where g.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Genre findById(long id) {
        return entityManager.find(Genre.class, id);
    }

    @Override
    public Genre findByName(String name) {
        TypedQuery<Genre> query = entityManager.createQuery("select g.id, g.name from Genre g where g.name like :name", Genre.class);
        query.setParameter("name", String.format("%s%s%s", "%", name, "%"));
        return query.getSingleResult();
    }

    @Override
    public List<Genre> findAll() {
        return entityManager.createQuery("select g.id, g.name from Genre g", Genre.class).getResultList();
    }

    @Override
    public int count() {
        return entityManager.createQuery("select count(g) from Genre g", int.class).getSingleResult();
    }
}
