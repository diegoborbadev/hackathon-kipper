package dev.diegoborba.hackathonkipper.model;

import dev.diegoborba.hackathonkipper.model.enums.QuestionCategory;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "questions")
public class Question extends IdentityGeneratorEntity<Long> {
    @Column(nullable = false)
    private String statement;

    @Column(nullable = false)
    private String correctAnswer;

    @Column(nullable = false)
    private String wrongAnswer1;

    @Column(nullable = false)
    private String wrongAnswer2;

    @Column(nullable = false)
    private String wrongAnswer3;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QuestionCategory category;
}