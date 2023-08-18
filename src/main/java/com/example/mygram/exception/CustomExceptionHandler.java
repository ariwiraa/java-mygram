package com.example.mygram.exception;

import com.example.mygram.model.dto.response.ResponsError;
import org.apache.tomcat.jni.LibraryNotFoundError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class CustomExceptionHandler {
    private ResponsError responseError;

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ResponsError> handleException(Exception e) {
        responseError = new ResponsError(500, e.getMessage(), LocalDateTime.now(), null);
        return ResponseEntity.internalServerError().body(responseError);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ResponsError> handlerNotFoundError(NotFoundException e) {
        responseError = new ResponsError(HttpStatus.NOT_FOUND.value(), e.getMessage(), LocalDateTime.now(), null);
        return ResponseEntity.status(responseError.getCode()).body(responseError);
    }
}
