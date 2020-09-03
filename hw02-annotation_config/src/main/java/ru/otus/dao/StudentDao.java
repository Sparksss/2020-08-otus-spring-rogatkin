package ru.otus.dao;


import java.util.List;

/**
 * Created by ilya on Aug, 2020
 */
public interface StudentDao {
    List<String> getQuestions();
    void registerStudent(String firstName, String lastName);
}
