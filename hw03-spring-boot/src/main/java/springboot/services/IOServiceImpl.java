package springboot.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import springboot.config.QuestionsConfig;

import java.io.IOException;
import java.io.InputStream;

@Component
public class IOServiceImpl implements IOService {

    private final String fileName;

    private static Logger logger = LoggerFactory.getLogger(IOServiceImpl.class);

    public IOServiceImpl(QuestionsConfig questionsConfig) {
        this.fileName = questionsConfig.getFileName();
    }

    @Override
    public String readFile() {
        final ClassLoader classLoader = getClass().getClassLoader();
        String content = null;
        try (InputStream stream = classLoader.getResourceAsStream(fileName)) {
            content = new String(stream.readAllBytes());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return content;
    }
}
