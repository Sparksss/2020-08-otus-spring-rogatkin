package springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Created by ilya on Oct, 2020
 */
@Configuration
public class ReaderConfig {
    @Bean
    public Reader reader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}
