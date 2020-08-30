package ru.otus.spring.service;

import ru.otus.spring.dao.StudentDao;

/**
 * Created by ilya on Aug, 2020
 */
public class StudentServiceImpl implements StudentService {

    private final StudentDao dao;

    public StudentServiceImpl(StudentDao dao) {
        this.dao = dao;
    }

    @Override
    public String getQuestions() {
        return this.dao.getQuestions();
    }
}
