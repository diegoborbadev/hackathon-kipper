package dev.diegoborba.hackathonkipper;

import dev.diegoborba.hackathonkipper.model.Question;
import dev.diegoborba.hackathonkipper.model.enums.QuestionCategory;
import dev.diegoborba.hackathonkipper.service.QuestionService;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class QuestionServiceIntegrationTest extends AbstractCrudTest<QuestionService, Question> {


    @Override
    protected Question createTestElement() {
        Question question = new Question();
        question.setStatement("Question ");
        question.setCorrectAnswer("Correct Answer ");
        question.setWrongAnswer1("Wrong Answer 1 ");
        question.setWrongAnswer2("Wrong Answer 2 ");
        question.setWrongAnswer3("Wrong Answer 3 ");
        question.setCategory(QuestionCategory.NATURE);
        return question;
    }

    @Override
    protected void updateTestElement(Question question) {
        question.setStatement("Statement Updated");
    }
}