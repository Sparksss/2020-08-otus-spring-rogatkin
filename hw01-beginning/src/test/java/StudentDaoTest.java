import org.gradle.internal.impldep.org.hamcrest.CoreMatchers;
import org.gradle.internal.impldep.org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.otus.spring.dao.StudentDao;
import ru.otus.spring.dao.StudentDaoImpl;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Created by ilya on Aug, 2020
 */
public class StudentDaoTest {

    static StudentDao studentDao;

    @BeforeAll
    public static void init() {
        studentDao = new StudentDaoImpl();
        studentDao.setFileName("questions.csv");
    }

    @Test
    public void simpleTest() {
        assertEquals(42, Integer.sum(19, 23));
    }

    @Test
    public void checkCorrectReadFile() {
        assertNotNull(studentDao.getQuestions());
    }

    @Test
    public void checkIfStringContainsQuestions() {
        Assert.assertThat(studentDao.getQuestions(), CoreMatchers.containsString("Вопросы"));
    }

    @Test
    public void checkStringContainsNeedQuestions() {
        String questions = studentDao.getQuestions();
        Assert.assertThat(questions, CoreMatchers.containsString("Какой сейчас год"));
        Assert.assertThat(questions, CoreMatchers.containsString("Формула дискриминанта"));
        Assert.assertThat(questions, CoreMatchers.containsString("Напишите столицу Австралии"));
        Assert.assertThat(questions, CoreMatchers.containsString("Напишите формулу определения Sin x"));
        Assert.assertThat(questions, CoreMatchers.containsString("Напишите формулу определения Cos x"));
    }
}
