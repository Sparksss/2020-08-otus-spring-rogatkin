package ru.otus.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import ru.otus.dao.StudentDao;
import ru.otus.domain.Student;

/**
 * Created by ilya on Sep, 2020
 */
@PropertySource("classpath:application.properties")
@Service
public class StudentServiceImpl implements StudentService {

    @Value("${student.grade}")
    private int GRADE;
    private final StudentDao dao;

    public StudentServiceImpl(StudentDao studentDaoImpl) {
        this.dao = studentDaoImpl;
    }

    private Student greeting() {
        System.out.println("Welcome!");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Student student = null;
        try {
            System.out.print("Please add your first name?: ");
            String firstName = reader.readLine();
            System.out.print("Please add your last name?: ");
            String lastName = reader.readLine();
            student = dao.save(firstName, lastName);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return student;
    }

    private int calculateRightAnswers(List<String> answers, List<String> studentAnswers) {
        int points = 0;
        for (int i = 0; i < studentAnswers.size(); i++) {
            if(answers.get(i).toLowerCase().trim().equals(studentAnswers.get(i).toLowerCase().trim())) {
                points++;
            }
        }
        return points;
    }

    private void showResultMessage(Student student) {
        System.out.println("Student name: " + student.getLastName() + ", " + student.getFirstName());
        System.out.println("Right answers: " + student.getCountRightAnswers());
        if(student.getCountRightAnswers() >= GRADE) {
            System.out.println("Congratulations, student is passed");
        } else {
            System.out.println("Unfortunately, the required number of points has not been reached");
        }
    }

    @Override
    public void testing() throws Exception {
        Student student = greeting();

        if(student == null) throw new Exception("Error create new profile student!");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> questions = dao.getQuestions();
        List<String> studentAnswers = new ArrayList<>();
        StringBuilder answer = new StringBuilder();

        for(String s : questions) {
            System.out.print(s + ": ");
            try {
                answer.append(reader.readLine());
                if(answer.length() > 1) {
                    studentAnswers.add(answer.toString());
                }
                answer.setLength(0);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        student.setCountRightAnswers(calculateRightAnswers(dao.getRightAnswers(), studentAnswers));
        showResultMessage(student);

    }

}
