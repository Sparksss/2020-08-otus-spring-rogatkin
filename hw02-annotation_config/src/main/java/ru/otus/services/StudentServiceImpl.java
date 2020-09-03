package ru.otus.services;

import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import ru.otus.dao.StudentDao;

/**
 * Created by ilya on Aug, 2020
 */
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentDao dao;

    public StudentServiceImpl(StudentDao dao) {
        this.dao = dao;
    }

    @Override
    public void greeting() {
        System.out.println("Здравствуйте!");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Пожалуйста введите Имя?: ");
            String firstName = reader.readLine();
            System.out.print("Пожалуйста введите Фамилию?: ");
            String lastName = reader.readLine();
//            dao.registerStudent(firstName, lastName);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public int testing() {
        List<String> questions = dao.getQuestions();
        for(String s : questions) {
            System.out.println(s);
        }
        return 0;
    }

}
