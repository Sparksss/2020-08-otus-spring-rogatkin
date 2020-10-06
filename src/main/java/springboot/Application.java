package springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springboot.services.StudentService;
import springboot.services.StudentServiceImpl;

/**
 * Created by ilya on Oct, 2020
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) throws Exception {
        var context = SpringApplication.run(Application.class);
        StudentService studentService = context.getBean(StudentServiceImpl.class);
        studentService.testing();
    }
}
