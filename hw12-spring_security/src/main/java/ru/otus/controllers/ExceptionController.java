package ru.otus.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.otus.exceptions.NotFoundException;
import ru.otus.exceptions.ValidateException;

/*
 * @created 13/12 - otus-spring
 * @author Ilya Rogatkin
 */
@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(ValidateException.class)
    public ResponseEntity<String> handleException(ValidateException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handlerNotFoundException(NotFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
