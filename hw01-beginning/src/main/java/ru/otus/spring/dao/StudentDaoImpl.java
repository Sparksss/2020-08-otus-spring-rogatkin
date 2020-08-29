package ru.otus.spring.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ilya on Aug, 2020
 */
public class StudentDaoImpl implements StudentDao {
    private String fileName;
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
    public String getQuestions() {
        String content = this.readFile();
        return content.replaceAll(",.+\n", "\n");
    }

    @Override
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


}
