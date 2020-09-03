package ru.otus.services;

import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import ru.otus.dao.StudentDao;

/**
 * Created by ilya on Sep, 2020
 */
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentDao dao;

    public StudentServiceImpl(StudentDao studentDaoImpl) {
        this.dao = studentDaoImpl;
    }

    private void greeting() {
        System.out.println("Здравствуйте!");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Пожалуйста введите Имя?: ");
            String firstName = reader.readLine();
            System.out.print("Пожалуйста введите Фамилию?: ");
            String lastName = reader.readLine();
            dao.registerStudent(firstName, lastName);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private int calculateRightAnswers(List<String> answers, List<String> studentAnswers) {
        int points = 0;
        for (int i = 0; i < studentAnswers.size(); i++) {
            if(answers.get(i).toLowerCase().equals(studentAnswers.get(i).toLowerCase())) {
                points++;
            }
        }
        return points;
    }

    @Override
    public int testing() {
        greeting();
        List<String> questions = dao.getQuestions();
        List<String> studentAnswers = new ArrayList<>();
        StringBuilder answer = new StringBuilder();
        for(String s : questions) {
            System.out.print(s);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                answer.append(reader.readLine());
                if(answer.length() > 1) {
                    studentAnswers.add(answer.toString());
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }


        return calculateRightAnswers(studentAnswers, dao.getRightAnswers());
    }

}
