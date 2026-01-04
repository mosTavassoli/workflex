package com.workflex.exceptions;

import com.workflex.error.ApiError;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ValidationError extends ApiError {

    private final Map<String, String> fieldErrors;

    public ValidationError(
            String message,
            String path,
            Map<String, String> fieldErrors
    ) {
        super(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                message,
                path
        );
        this.fieldErrors = fieldErrors;
    }

    public static ValidationError from(MethodArgumentNotValidException ex, String path) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(e -> errors.put(e.getField(), e.getDefaultMessage()));

        return new ValidationError("Validation failed", path, errors);
    }
}