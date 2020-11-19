package ru.otus;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    private static final String URL = "jdbc:postgresql://localhost:5432/test";
    private static final String USER = "test";
    private static final String PASSWORD = "12345";

    public static void main(String[] args) {
        flyWayMigrations(URL, USER , PASSWORD);
        SpringApplication.run(Application.class);
    }


    public static void flyWayMigrations(String url, String user, String password) {
        Flyway flyway = Flyway.configure()
                .dataSource(url, user, password)
                .locations("classpath:/db/migration")
                .load();
        flyway.migrate();
    }
}
