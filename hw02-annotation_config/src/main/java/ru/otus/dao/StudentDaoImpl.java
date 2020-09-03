package ru.otus.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Student;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ilya on Aug, 2020
 */
@Repository
public class StudentDaoImpl implements StudentDao {

    private String fileName = "questions.csv";
    private static Logger logger = LoggerFactory.getLogger(StudentDaoImpl.class);

    private final ClassLoader classLoader = getClass().getClassLoader();

    private String readFile() {
        String content = null;
        try (InputStream stream = classLoader.getResourceAsStream(fileName)) {
            content = new String(stream.readAllBytes());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return content;
    }

    @Override
    public List<String> getQuestions() {
        String content = this.readFile();
        List<String> questions = new ArrayList<>();
        content = content.replaceAll("(,.+\n)", "\n");
        String[] str = content.split("\n");
        for(int i = 1; i < str.length; i++) {
            questions.add(str[i]);
        }
        return questions;
    }

    @Override
    public List<String> getRightAnswers() {
        String content = this.readFile();
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
