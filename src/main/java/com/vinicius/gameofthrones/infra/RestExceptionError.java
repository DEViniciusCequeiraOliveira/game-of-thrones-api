package com.vinicius.gameofthrones.infra;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class RestExceptionError {

    @ExceptionHandler(NoSuchElementException.class)
    private ResponseEntity<RestMensageError> optionalEmpty(NoSuchElementException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RestMensageError(HttpStatus.NOT_FOUND,"Id Not Found"));
    }

    @ExceptionHandler(BadCredentialsException.class)
    private ResponseEntity<RestMensageError> badCredentials(BadCredentialsException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RestMensageError(HttpStatus.NOT_FOUND,"email or passWord invalido"));
    }

}
