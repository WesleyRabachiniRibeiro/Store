package com.store.purchase.controllers;

import com.store.purchase.models.ErrorPresenter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.sql.SQLException;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler({SQLException.class, Exception.class})
    public ResponseEntity<ErrorPresenter> handleException(Exception ex) {
        return new ResponseEntity<>(new ErrorPresenter(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler({HttpMessageNotReadableException.class, RuntimeException.class})
    public ResponseEntity<ErrorPresenter> handleHttpMessageNotReadableException(Exception ex) {
        return new ResponseEntity(new ErrorPresenter(HttpStatus.BAD_REQUEST.value(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorPresenter> handleForbiddenException(AccessDeniedException ex) {
        return new ResponseEntity<>(new ErrorPresenter(HttpStatus.FORBIDDEN.value(), ex.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorPresenter> handleForbiddenException(ResponseStatusException ex) {
        return new ResponseEntity<>(new ErrorPresenter(HttpStatus.NOT_FOUND.value(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}
