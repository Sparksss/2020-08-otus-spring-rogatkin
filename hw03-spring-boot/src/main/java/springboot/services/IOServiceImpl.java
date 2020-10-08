package springboot.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import springboot.config.LocaleConfig;
import springboot.config.QuestionsConfig;

import java.io.IOException;
import java.io.InputStream;

@Component
public class IOServiceImpl implements IOService {

    private final String fileName;
    private final LocaleConfig localeConfig;

    private static Logger logger = LoggerFactory.getLogger(IOServiceImpl.class);

    public IOServiceImpl(QuestionsConfig questionsConfig, LocaleConfig localeConfig) {
        this.fileName = questionsConfig.getFileName();
        this.localeConfig = localeConfig;
    }

    @Override
    public String readFile() {
        final ClassLoader classLoader = getClass().getClassLoader();
        String content = null;
        try (InputStream stream = classLoader.getResourceAsStream("i18n/"+ localeConfig.getLocale().toString() + '/' + fileName)) {
            content = new String(stream.readAllBytes());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return content;
    }
}
