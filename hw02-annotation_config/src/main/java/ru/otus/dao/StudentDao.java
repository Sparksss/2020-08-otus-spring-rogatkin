package ru.otus.dao;


import ru.otus.domain.Student;

import java.util.List;

/**
 * Created by ilya on Sep, 2020
 */
public interface StudentDao {
    List<String> getQuestions();
    List<String> getRightAnswers();
    Student save(String firstName, String lastName);
}
