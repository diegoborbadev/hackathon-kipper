package dev.diegoborba.hackathonkipper.controller;

import dev.diegoborba.hackathonkipper.dto.QuestionDto;
import dev.diegoborba.hackathonkipper.model.Question;
import dev.diegoborba.hackathonkipper.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/questions")
@AllArgsConstructor
public class QuestionController extends CrudController<QuestionService, QuestionDto, Question> {

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