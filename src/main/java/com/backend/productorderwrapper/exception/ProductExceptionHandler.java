package com.backend.productorderwrapper.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class ProductExceptionHandler extends ResponseEntityExceptionHandler {

   @ExceptionHandler(value = { HttpClientErrorException.class })
   public ResponseEntity<Object> handleEntityNotFound() {
       return ResponseEntity.status(NOT_FOUND).body(ApiException.newBuilder()
               .withStatus(NOT_FOUND)
               .withMessage("Resource not found")
               .build());
   }

    @ExceptionHandler(value = { HttpServerErrorException.class })
    public ResponseEntity<Object> handleExternalServerError() {
        return ResponseEntity.status(BAD_GATEWAY).body(ApiException.newBuilder()
                .withStatus(BAD_GATEWAY)
                .withMessage("External api error")
                .build());
    }

    @ExceptionHandler(value = { ResourceAccessException.class })
    public ResponseEntity<Object> handleExternalError() {
        return ResponseEntity.status(GATEWAY_TIMEOUT).body(ApiException.newBuilder()
                .withStatus(GATEWAY_TIMEOUT)
                .withMessage("External service timeout")
                .build());
    }

    @ExceptionHandler(value = { NullPointerException.class })
    public ResponseEntity<Object> internalApiError() {
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(ApiException.newBuilder()
                .withStatus(INTERNAL_SERVER_ERROR)
                .withMessage("Something went wrong")
                .build());
    }
}