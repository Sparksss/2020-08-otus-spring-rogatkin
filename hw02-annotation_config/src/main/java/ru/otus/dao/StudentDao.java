package ru.otus.dao;


import java.util.List;

/**
 * Created by ilya on Sep, 2020
 */
public interface StudentDao {
    List<String> getQuestions();
    List<String> getRightAnswers();
    void registerStudent(String firstName, String lastName);
}
