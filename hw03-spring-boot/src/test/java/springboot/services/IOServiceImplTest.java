package springboot.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by ilya on Oct, 2020
 */
@SpringBootTest
@DisplayName("Проверяем что сервис ввода/вывода")
class IOServiceImplTest {

    @Autowired
    IOService ioService;

    int ZERO = 0;

    @Test
    @DisplayName("Возвращает не пустую строку")
    void getNotEmptyString() {
        assertThat(ioService.readFile().length()).isGreaterThan(ZERO);
    }
}