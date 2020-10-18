package springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import springboot.config.LocaleConfig;
import springboot.config.QuestionsConfig;
import springboot.config.StudentConfig;
import springboot.services.StudentService;
import springboot.services.StudentServiceImpl;

/**
 * Created by ilya on Oct, 2020
 */
@SpringBootApplication
@EnableConfigurationProperties({QuestionsConfig.class, StudentConfig.class, LocaleConfig.class})
public class Application {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
