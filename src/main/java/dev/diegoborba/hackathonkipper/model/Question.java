package dev.diegoborba.hackathonkipper.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
}