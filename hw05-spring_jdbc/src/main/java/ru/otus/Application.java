package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


/**
 * Created by ilya on Oct, 2020
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) throws Exception {
       ConfigurableApplicationContext ctx =  SpringApplication.run(Application.class);
    }
}
