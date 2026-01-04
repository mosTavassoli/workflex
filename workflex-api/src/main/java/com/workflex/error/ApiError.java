package com.workflex.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Getter
public class ApiError {

    private final int status;
    private final String error;
    private final String message;
    private final String path;
    private final Instant timestamp;

    protected ApiError(
            int status,
            String error,
            String message,
            String path
    ) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.timestamp = Instant.now();
    }

    public static ApiError of(
            HttpStatus status,
            String message,
            String path
    ) {
        return new ApiError(
                status.value(),
                status.getReasonPhrase(),
                message,
                path
        );
    }
}
