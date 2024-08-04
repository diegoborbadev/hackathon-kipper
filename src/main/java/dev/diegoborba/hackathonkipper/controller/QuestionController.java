package dev.diegoborba.hackathonkipper.controller;

import dev.diegoborba.hackathonkipper.dto.QuestionDto;
import dev.diegoborba.hackathonkipper.model.Question;
import dev.diegoborba.hackathonkipper.model.enums.QuestionCategory;
import dev.diegoborba.hackathonkipper.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/questions")
@AllArgsConstructor
public class QuestionController extends CrudController<QuestionService, QuestionDto, Question> {

    @Operation(summary = "Get random questions by category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the elements", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Invalid pageable parameter(s) supplied", content = @Content),
            @ApiResponse(responseCode = "204", description = "No items found", content = @Content)
    })
    @GetMapping("/random/{category}")
    public ResponseEntity<List<QuestionDto>> getRandomQuestionsByCategory(
                                                                @Parameter(description = "Questions category", required = true) @PathVariable QuestionCategory category,
                                                                @Parameter(description = "List size", required = true) @RequestParam Integer size) {
        List<Question> questions = service.getRandomQuestionsByCategory(category, size);
        return questions.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(convertToListDto(questions));
    }

    @Override
    protected List<QuestionDto> convertToListDto(List<Question> elements) {
        return modelMapper.map(elements, DTO_LIST_TYPE_TOKEN);
    }

    @Override
    protected QuestionDto convertToDetailDto(Question element) {
        return modelMapper.map(element, QuestionDto.class);
    }

    @Override
    protected Question convertToModel(QuestionDto QuestionDto) {
        return modelMapper.map(QuestionDto, Question.class);
    }
}