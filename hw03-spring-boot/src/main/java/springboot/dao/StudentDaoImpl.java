package springboot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import springboot.domain.Student;
import springboot.services.IOService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilya on Aug, 2020
 */

@Repository
public class StudentDaoImpl implements StudentDao {

    private IOService ioService;

    public StudentDaoImpl() {
    }

    @Autowired
    public StudentDaoImpl(IOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public List<String> getQuestions() {
        String content = ioService.readFile();
        List<String> questions = new ArrayList<>();
        String[] str = content.split("\n");

        for(int i = 1; i < str.length; i++) {
            questions.add(str[i].replaceAll("(,.+)", ""));
        }
        return questions;
    }

    @Override
    public List<String> getRightAnswers() {
        String content = ioService.readFile();
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
