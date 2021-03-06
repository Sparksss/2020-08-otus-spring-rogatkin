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
    private final EntityManager entityManager;

    public GenreRepositoryJPAImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

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
    public void delete(Genre genre) {
        this.entityManager.remove(genre);
    }

    @Override
    public Genre findById(long id) {
        return entityManager.find(Genre.class, id);
    }

    @Override
    public Genre findByName(String name) {
        TypedQuery<Genre> query = entityManager.createQuery("select g from Genre g where g.name like :name", Genre.class);
        query.setParameter("name", String.format("%s%s%s", "%", name, "%"));
        return query.getSingleResult();
    }

    @Override
    public List<Genre> findAll() {
        return entityManager.createQuery("select g from Genre g", Genre.class).getResultList();
    }

    @Override
    public Long count() {
        return entityManager.createQuery("select count(g) from Genre g", Long.class).getSingleResult();
    }
}
