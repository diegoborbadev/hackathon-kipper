package dev.diegoborba.hackathonkipper.controller;

import dev.diegoborba.hackathonkipper.model.BaseModel;
import dev.diegoborba.hackathonkipper.service.CrudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public abstract class CrudController<S extends CrudService<T, Long>, DTO, T extends BaseModel<Long>> {

    protected final Type DTO_LIST_TYPE_TOKEN = new TypeToken<List<DTO>>(){}.getType();

    @Autowired
    protected ModelMapper modelMapper;

    @Autowired
    protected S service;

    @Operation(summary = "Get all elements")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the elements", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "204", description = "No items found", content = @Content)
    })
    @GetMapping("/all")
    @Transactional
    public ResponseEntity<List<DTO>> getAllElements() {
        List<T> elements = service.findAll();
        if (!CollectionUtils.isEmpty(elements)) {
            return ResponseEntity.ok(convertToListDto(elements));
        }
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get all elements")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the elements", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Invalid pageable information supplied", content = @Content),
            @ApiResponse(responseCode = "204", description = "No items found", content = @Content)
    })
    @GetMapping("/all/pagination")
    @Transactional
    public ResponseEntity<Page<DTO>> getAllElements(
            @Parameter(description = "Page number", required = true) @RequestParam(defaultValue = "0") Integer page,
            @Parameter(description = "Page size", required = true) @RequestParam Integer size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<T> pages = service.findAll(pageable);
        if (pages != null && pages.hasContent()) {
            List<DTO> dtoList = convertToListDto(pages.getContent());
            return ResponseEntity.ok(new PageImpl<>(dtoList, pageable, pages.getTotalElements()));
        }
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get element by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the element", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "404", description = "Element not found", content = @Content)
    })
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<DTO> getElementById(@PathVariable Long id) {
        Optional<T> element = service.findById(id);
        return buildResponseEntityFromOptional(element);
    }

    @Operation(summary = "Create a new element")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Element created", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Invalid request body", content = @Content)
    })
    @PostMapping("/")
    @Transactional
    public ResponseEntity<DTO> createElement(@Valid @RequestBody DTO element) {
        T converted = convertToModel(element);
        T elementCreated = service.createElement(converted);
        if (elementCreated != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToDetailDto(elementCreated));
        }
        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Update a element")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Element updated", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Invalid request body", content = @Content),
            @ApiResponse(responseCode = "404", description = "Element not found", content = @Content)
    })
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DTO> updateElement(@PathVariable Long id, @Valid @RequestBody DTO element) {
        T converted = convertToModel(element);
        Optional<T> elementUpdated = service.updateElement(id, converted);
        return buildResponseEntityFromOptional(elementUpdated);
    }

    @Operation(summary = "Delete a element")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Element deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Element not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteElement(@PathVariable Long id) {
        boolean success = service.deleteElement(id);
        if (success) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    protected abstract List<DTO> convertToListDto(List<T> elements);

    protected abstract DTO convertToDetailDto(T element);

    protected abstract T convertToModel(DTO dto);

    protected ResponseEntity<DTO> buildResponseEntityFromOptional(Optional<T> element) {
        return element.map(t -> ResponseEntity.ok(convertToDetailDto(t))).orElseGet(() -> ResponseEntity.notFound().build());
    }
}