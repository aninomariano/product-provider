package com.backend.productorderwrapper.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

public class ApiException {

    private HttpStatus status;
    private String message;

    public ApiException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
