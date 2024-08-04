package dev.diegoborba.hackathonkipper.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto {
    @Schema(description = "Id", example = "12", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "User name", example = "Pedro")
    @NotBlank(message = "Filling in 'name' is mandatory")
    private String name;

    @Schema(description = "User score", example = "435", accessMode = Schema.AccessMode.READ_ONLY)
    private Long score;
}