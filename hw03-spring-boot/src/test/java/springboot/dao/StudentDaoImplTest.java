package springboot.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by ilya on Oct, 2020
 */
@SpringBootTest
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