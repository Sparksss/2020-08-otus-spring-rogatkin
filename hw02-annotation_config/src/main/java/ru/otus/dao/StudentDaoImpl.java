package ru.otus.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Student;

import java.io.IOException;
import java.io.InputStream;
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
        content = content.replaceAll(",.+\n", "\n");
        return Arrays.asList(content.split("\n"));
    }

    @Override
    public void registerStudent(String firstName, String lastName) {
        new Student(firstName, lastName);
    }
}
