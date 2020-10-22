package springboot.services;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import springboot.config.LocaleConfig;
import springboot.config.StudentConfig;
import springboot.dao.StudentDao;
import springboot.domain.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by ilya on Sep, 2020
 */
@Service
public class StudentServiceImpl implements StudentService {

    private final int GRADE;
    private final StudentDao dao;
    private final MessageSource messageSource;
    private final Locale locale;
    private final IOService ioService;

    public StudentServiceImpl(StudentDao studentDaoImpl, StudentConfig studentConfig, MessageSource messageSource, LocaleConfig localeConfig, IOService ioService) {
        this.GRADE = studentConfig.getGrade();
        this.dao = studentDaoImpl;
        this.messageSource = messageSource;
        this.locale = localeConfig.getLocale();
        this.ioService = ioService;
    }

    @Override
    public void testing() throws Exception {
        Student student = greeting();

        if(student == null) throw new Exception("Error create new profile student!");

        List<String> questions = dao.getQuestions();
        List<String> studentAnswers = new ArrayList<>();
        StringBuilder answer = new StringBuilder();

        for(String question : questions) {
            System.out.print(question);
            try {
                answer.append(this.ioService.readString());
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

    public Student greeting() {
        System.out.println(messageSource.getMessage("message.user.greeting",new String[]{": "} , locale));
        Student student = null;
        try {
            System.out.print(messageSource.getMessage("message.user.enterName",new String[]{": "} , locale));
            String firstName = ioService.readString();
            System.out.print(messageSource.getMessage("message.user.lastName",new String[]{": "} , locale));
            String lastName = ioService.readString();
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
        System.out.println(messageSource.getMessage("message.user.name", new String[]{String.format("%s%s%s", student.getLastName(),", ",student.getFirstName())}, locale));
        System.out.println(messageSource.getMessage("message.rightAnswers.count", new String[]{Integer.toString(student.getCountRightAnswers())}, locale));
        if( student.getCountRightAnswers() >= GRADE) {
            System.out.println(messageSource.getMessage("message.congratulations", new String[]{":"}, locale));
        } else {
            System.out.println(messageSource.getMessage("message.lose", new String[]{":"}, locale));
        }
    }

}
