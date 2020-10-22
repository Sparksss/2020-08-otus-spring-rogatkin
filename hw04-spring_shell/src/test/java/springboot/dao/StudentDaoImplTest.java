package springboot.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by ilya on Oct, 2020
 */
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@DisplayName("Проверяем что сервис StudentDao")
class StudentDaoImplTest {

    @Autowired
    StudentDao studentDao;

    static final int COUNT_QUESTIONS = 5;
    static final int COUNT_ANSWERS = 5;

    @Test
    @DisplayName("выдаёт одинаковое количество вопросов что и в csv файле")
    void checkQuestionsCount() {
        assertEquals(studentDao.getQuestions().size(), COUNT_QUESTIONS);
    }

    @Test
    @DisplayName("выдаёт одинаковое количество ответов что и в csv файле")
    void checkAnswersCount() {
        assertEquals(studentDao.getRightAnswers().size(), COUNT_ANSWERS);
    }
}