package com.backend.productorderwrapper.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_GATEWAY;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ProductExceptionHandler extends ResponseEntityExceptionHandler {

   @ExceptionHandler(value = { NotFoundException.class })
   public ResponseEntity<Object> handleEntityNotFound() {
       final ApiException apiException = new ApiException(NOT_FOUND, "Products not found");
       return ResponseEntity.status(NOT_FOUND).body(apiException);
   }

    @ExceptionHandler(value = { ExternalApiErrorException.class })
    public ResponseEntity<Object> handleExternalServerError() {
        final ApiException apiException = new ApiException(NOT_FOUND, "Something went wrong with Product external service");
        return ResponseEntity.status(BAD_GATEWAY).body(apiException);
    }
}