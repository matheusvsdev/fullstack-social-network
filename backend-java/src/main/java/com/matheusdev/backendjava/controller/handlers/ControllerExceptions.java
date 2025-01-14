package com.matheusdev.backendjava.controller.handlers;

import com.matheusdev.backendjava.dto.CustomError;
import com.matheusdev.backendjava.dto.ValidationError;
import com.matheusdev.backendjava.exceptions.ArgumentAlreadyExistsException;
import com.matheusdev.backendjava.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptions {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError error = new CustomError(Instant.now()
                , status.value()
                , e.getMessage()
                , request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(ArgumentAlreadyExistsException.class)
    public ResponseEntity<CustomError> argumentAlreadyExists(ArgumentAlreadyExistsException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        CustomError error = new CustomError(Instant.now()
                , status.value()
                , e.getMessage()
                , request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus http = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError error = new ValidationError(Instant.now()
                ,http.value()
                ,"Invalid data"
                ,request.getRequestURI());

        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            error.addError(f.getField(), f.getDefaultMessage());
        }

        return ResponseEntity.status(http).body(error);
    }
}
