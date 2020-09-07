package ru.otus.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class IOServiceImpl implements IOService {

    @Value("${file.name}")
    private String fileName;

    private static Logger logger = LoggerFactory.getLogger(IOServiceImpl.class);

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
