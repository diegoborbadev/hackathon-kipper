package dev.diegoborba.hackathonkipper.repository;

import dev.diegoborba.hackathonkipper.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}