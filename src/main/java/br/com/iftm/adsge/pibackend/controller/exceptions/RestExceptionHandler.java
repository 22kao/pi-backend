package br.com.iftm.adsge.pibackend.controller.exceptions;

import br.com.iftm.adsge.pibackend.service.exceptions.DatabaseException;
import br.com.iftm.adsge.pibackend.service.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError standardError = StandardError.builder()
                .status(status.value())
                .error(ErrorMessage.RESOURCE_NOT_FOUND.getMessage())
                .message(e.getMessage())
                .timestamp(Instant.now())
                .path(request.getRequestURI()).build();

        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError standardError = StandardError.builder()
                .status(status.value())
                .error(ErrorMessage.DATABASE.getMessage())
                .message(e.getMessage())
                .timestamp(Instant.now())
                .path(request.getRequestURI()).build();

        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> general(Exception e, HttpServletRequest request){

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        StandardError standardError = StandardError.builder()
                .status(status.value())
                .error(ErrorMessage.INTERNAL.getMessage())
                .message(e.getMessage())
                .timestamp(Instant.now())
                .path(request.getRequestURI()).build();

        return ResponseEntity.status(status).body(standardError);
    }


}