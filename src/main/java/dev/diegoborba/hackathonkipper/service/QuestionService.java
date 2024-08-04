package dev.diegoborba.hackathonkipper.service;

import dev.diegoborba.hackathonkipper.model.Question;
import dev.diegoborba.hackathonkipper.repository.QuestionRepository;
import org.springframework.stereotype.Service;

@Service
public class QuestionService extends CrudServiceJpaImpl<QuestionRepository, Question, Long> {

}