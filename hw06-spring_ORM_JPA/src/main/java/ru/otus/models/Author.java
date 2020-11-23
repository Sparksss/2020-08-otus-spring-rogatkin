package ru.otus.models;

import lombok.*;

import javax.persistence.*;

/**
 * Created by ilya on Oct, 2020
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    public Author(String name) {
        this.name = name;
    }
}
