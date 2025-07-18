package com.vinicius.gameofthrones.infra;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.NoSuchElementException;

@ControllerAdvice
public class RestExceptionError {

    @ExceptionHandler(NoSuchElementException.class)
    private ResponseEntity<RestMensageError> optionalEmpty(NoSuchElementException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RestMensageError(HttpStatus.NOT_FOUND, "Id Not Found"));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    private ResponseEntity<RestMensageError> optionalEmpty(HttpRequestMethodNotSupportedException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RestMensageError(HttpStatus.NOT_FOUND, exception.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestMensageError> handleAllOtherExceptions(Exception ex) {
        RestMensageError error = new RestMensageError(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error: " + ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public RestMensageError handleNoHandlerFound(NoHandlerFoundException ex) {
        return new RestMensageError(HttpStatus.NOT_FOUND, "Resource not found: " + ex.getMessage());
    }
}
