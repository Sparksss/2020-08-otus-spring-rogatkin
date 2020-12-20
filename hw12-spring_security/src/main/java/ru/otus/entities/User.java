package ru.otus.entities;

import lombok.Data;
import ru.otus.enums.Role;

import javax.persistence.*;

/*
 * @created 13/12 - otus-spring
 * @author Ilya Rogatkin
 */
@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

}
