package ru.otus;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.services.StudentService;
import ru.otus.services.StudentServiceImpl;

/**
 * Created by ilya on Sep, 2020
 */
@ComponentScan
public class Main {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        StudentService studentService = context.getBean(StudentServiceImpl.class);
        System.out.println("Количество правильных ответов: " + studentService.testing());
    }
}
