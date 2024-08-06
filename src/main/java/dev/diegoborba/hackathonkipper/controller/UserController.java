package dev.diegoborba.hackathonkipper.controller;

import dev.diegoborba.hackathonkipper.dto.UserDto;
import dev.diegoborba.hackathonkipper.model.User;
import dev.diegoborba.hackathonkipper.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/users")
@AllArgsConstructor
public class UserController extends CrudController<UserService, UserDto, User> {

    @Operation(summary = "Update user name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Element updated", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "404", description = "Element not found", content = @Content)
    })
    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<UserDto> updateElement(@PathVariable Long id,
                                                 @Parameter(description = "New name", required = true) @RequestParam String name) {

        Optional<User> updatedUser = service.updateName(id, name);
        return buildResponseEntityFromOptional(updatedUser);
    }

    @Operation(summary = "Update user score")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Element updated", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "404", description = "Element not found", content = @Content)
    })
    @PatchMapping("/{id}/score/add")
    public ResponseEntity<UserDto> addScore(@PathVariable Long id,
                                            @Parameter(description = "Score to be added to (or removed from) the user's total score", required = true)
                                                @RequestParam Long value) {

        Optional<User> updatedUser = service.addScore(id, value);
        return buildResponseEntityFromOptional(updatedUser);
    }

    @Override
    protected List<UserDto> convertToListDto(List<User> elements) {
        return modelMapper.map(elements, DTO_LIST_TYPE_TOKEN);
    }

    @Override
    protected UserDto convertToDetailDto(User element) {
        return modelMapper.map(element, UserDto.class);
    }

    @Override
    protected User convertToModel(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }
}
