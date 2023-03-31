package com.store.user.controllers;

import com.store.user.exception.StatusException;
import com.store.user.models.ErrorPresenter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.AccessDeniedException;
import java.sql.SQLException;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ErrorPresenter> handleException(Exception ex) {
        return new ResponseEntity<>(new ErrorPresenter(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, RuntimeException.class, StatusException.class})
    public ResponseEntity<ErrorPresenter> handleHttpMessageNotReadableException(Exception ex) {
        return new ResponseEntity<>(new ErrorPresenter(HttpStatus.BAD_REQUEST.value(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorPresenter> handleForbiddenException(AccessDeniedException ex) {
        return new ResponseEntity<>(new ErrorPresenter(HttpStatus.FORBIDDEN.value(), ex.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({ResponseStatusException.class, UsernameNotFoundException.class})
    public ResponseEntity<ErrorPresenter> handleForbiddenException(Exception ex) {
        return new ResponseEntity<>(new ErrorPresenter(HttpStatus.NOT_FOUND.value(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }

}
