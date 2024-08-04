package dev.diegoborba.hackathonkipper.repository;

import dev.diegoborba.hackathonkipper.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(value = "SELECT * FROM questions q WHERE q.category = :category ORDER BY RANDOM() LIMIT :size", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, Integer size);
}