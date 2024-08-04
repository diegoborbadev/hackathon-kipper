package dev.diegoborba.hackathonkipper.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class QuestionDto {
    @Schema(description = "Id", example = "12", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Question statement", example = "Which of the following is a primary producer in an ecosystem?")
    @NotBlank(message = "Filling in 'statement' is mandatory")
    private String statement;

    @Schema(description = "Correct Answer", example = "Oak Tree")
    @NotBlank(message = "Filling in 'correctAnswer' is mandatory")
    private String correctAnswer;

    @Schema(description = "Wrong Answer 1", example = "Mushroom")
    @NotBlank(message = "Filling in 'wrongAnswer1' is mandatory")
    private String wrongAnswer1;

    @Schema(description = "Wrong Answer 2", example = "Lion")
    @NotBlank(message = "Filling in 'wrongAnswer2' is mandatory")
    private String wrongAnswer2;

    @Schema(description = "Wrong Answer 3", example = "Earthworm")
    @NotBlank(message = "Filling in 'wrongAnswer3' is mandatory")
    private String wrongAnswer3;
}