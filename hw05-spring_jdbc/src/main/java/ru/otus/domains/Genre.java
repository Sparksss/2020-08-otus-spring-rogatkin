package ru.otus.domains;

/**
 * Created by ilya on Oct, 2020
 */
public class Genre {
    private short id;
    private String name;

    public Genre() {
    }

    public Genre(short id, String name) {
        this.id = id;
        this.name = name;
    }

    public short getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(short id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
