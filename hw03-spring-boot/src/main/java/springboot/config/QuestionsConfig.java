package springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by ilya on Oct, 2020
 */
@ConfigurationProperties(prefix = "questions")
public class QuestionsConfig {
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
