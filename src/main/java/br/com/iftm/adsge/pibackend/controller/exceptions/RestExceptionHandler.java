package br.com.iftm.adsge.pibackend.controller.exceptions;

import br.com.iftm.adsge.pibackend.service.exceptions.DatabaseException;
import br.com.iftm.adsge.pibackend.service.exceptions.ResourceNotFoundException;
import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
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

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardError> constraintViolation(ConstraintViolationException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError standardError = StandardError.builder()
                .status(status.value())
                .error(ErrorMessage.DATABASE.getMessage())
                .message(e.getMessage())
                .timestamp(Instant.now())
                .path(request.getRequestURI()).build();

        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> argumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String msgError = e.getBindingResult().getFieldError().getDefaultMessage();
        StandardError standardError = StandardError.builder()
                .status(status.value())
                .error(ErrorMessage.ARGUMENT_INVALID.getMessage())
                .message(msgError)
                .timestamp(Instant.now())
                .path(request.getRequestURI()).build();

        return ResponseEntity.status(status).body(standardError);

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> jsonParse(HttpMessageNotReadableException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError standardError = StandardError.builder()
                .status(status.value())
                .error(ErrorMessage.JSON_PARSE_ERROR.getMessage())
                .message(e.getMessage())
                .timestamp(Instant.now())
                .path(request.getRequestURI()).build();

        return ResponseEntity.status(status).body(standardError);
    }
}
