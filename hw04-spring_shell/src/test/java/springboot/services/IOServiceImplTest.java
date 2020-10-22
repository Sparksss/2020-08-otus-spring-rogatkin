package springboot.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by ilya on Oct, 2020
 */
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
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