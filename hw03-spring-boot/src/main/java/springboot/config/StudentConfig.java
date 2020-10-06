package springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ilya on Oct, 2020
 */
@Configuration
@ConfigurationProperties(prefix = "student")
public class StudentConfig {
    private int grade;

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
