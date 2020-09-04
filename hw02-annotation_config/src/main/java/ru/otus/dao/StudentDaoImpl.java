package ru.otus.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilya on Aug, 2020
 */

@Repository
public class StudentDaoImpl implements StudentDao {

    private static Logger logger = LoggerFactory.getLogger(StudentDaoImpl.class);

    private final String content;

    @Autowired
    public StudentDaoImpl(String content) {
        this.content = content;
    }

    @Override
    public List<String> getQuestions() {
        List<String> questions = new ArrayList<>();
        String[] str = content.split("\n");

        for(int i = 1; i < str.length; i++) {
            questions.add(str[i].replaceAll("(,.+)", ""));
        }
        return questions;
    }

    @Override
    public List<String> getRightAnswers() {
        String[] str = content.split(",|\n");
        List<String> answers = new ArrayList<>();
        for(int i = 4; i < str.length; i += 3) {
            answers.add(str[i]);
        }
        return answers;
    }

    @Override
    public Student save(String firstName, String lastName) {
        return new Student(firstName, lastName);
    }
}
