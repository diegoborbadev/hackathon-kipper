package dev.diegoborba.hackathonkipper.service;

import dev.diegoborba.hackathonkipper.model.Question;
import dev.diegoborba.hackathonkipper.model.enums.QuestionCategory;
import dev.diegoborba.hackathonkipper.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService extends CrudServiceJpaImpl<QuestionRepository, Question, Long> {

    public List<Question> getRandomQuestionsByCategory(QuestionCategory category, Integer size) {
        return repository.findRandomQuestionsByCategory(category.name(), size);
    }
}