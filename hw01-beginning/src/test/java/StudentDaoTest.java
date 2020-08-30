import org.hamcrest.CoreMatchers;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
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
        System.out.println(studentDao.getQuestions());
        assertThat(studentDao.getQuestions(), CoreMatchers.containsString("Вопросы"));
    }

    @Test
    public void checkStringContainsNeedQuestions() {
        String questions = studentDao.getQuestions();
        assertThat(questions, CoreMatchers.containsString("Какой сейчас год"));
        assertThat(questions, CoreMatchers.containsString("Формула дискриминанта"));
        assertThat(questions, CoreMatchers.containsString("Напишите столицу Австралии"));
        assertThat(questions, CoreMatchers.containsString("Напишите формулу определения Sin x"));
        assertThat(questions, CoreMatchers.containsString("Напишите формулу определения Cos x"));
    }
}
