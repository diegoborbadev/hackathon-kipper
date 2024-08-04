package dev.diegoborba.hackathonkipper.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();

        Map<String, String> errors = bindingResult.getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

        return new ResponseEntity<>(Map.of("error", errors), HttpStatus.BAD_REQUEST);
    }
}