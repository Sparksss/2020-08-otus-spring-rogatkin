package ru.otus.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
 * @created 07/12 - otus-spring
 * @author Ilya Rogatkin
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "genres")
public class Genre {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    public Genre(String name) {
        this.name = name;
    }

    public Genre(long id, String name) {
        this.id = id;
        this.name = name;
    }
}