package springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import springboot.services.StudentService;

@ShellComponent
public class StudentShell {

    private final StudentService studentService;

    private boolean isFinal;

    @Autowired
    public StudentShell(StudentService studentService) {
        this.studentService = studentService;
    }

    @ShellMethod(value = "Start testing student application", key = {"s", "start"})
    public void start() throws Exception {
        studentService.testing();
    }

}
