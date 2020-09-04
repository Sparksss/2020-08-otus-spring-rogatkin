package ru.otus.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ilya on Sep, 2020
 */
@Configuration
public class IOConfig {

    @Value("${file.name}")
    private String fileName;
    private static Logger logger = LoggerFactory.getLogger(IOConfig.class);

    @Bean
    public String content() {
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
