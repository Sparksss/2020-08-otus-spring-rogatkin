package springboot.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import springboot.config.LocaleConfig;
import springboot.config.QuestionsConfig;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

@Component
public class IOServiceImpl implements IOService {

    private final String fileName;
    private final LocaleConfig localeConfig;
    private final Reader reader;
    private static final int NEXT_LINE = 10;

    private static Logger logger = LoggerFactory.getLogger(IOServiceImpl.class);

    public IOServiceImpl(QuestionsConfig questionsConfig, LocaleConfig localeConfig, Reader reader) {
        this.fileName = questionsConfig.getFileName();
        this.localeConfig = localeConfig;
        this.reader = reader;
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


    public String readString() throws IOException {
        StringBuilder line = new StringBuilder();
        int s = reader.read();
        while (s != NEXT_LINE) {
            line.append((char) s);
            s = reader.read();
        }
        return line.toString();
    }
}
