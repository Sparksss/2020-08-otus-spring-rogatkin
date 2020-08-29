package ru.otus;


import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.service.StudentService;

/**
 * Created by ilya on Aug, 2020
 */
public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        StudentService service = context.getBean(StudentService.class);
        System.out.println(service.getQuestions());
    }
}
