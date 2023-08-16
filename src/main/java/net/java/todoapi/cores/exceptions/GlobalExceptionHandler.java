package net.java.todoapi.cores.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<ErrorObject> HandleProjectNotFoundException(ProjectNotFoundException ex) {
        var errorObj = new ErrorObject(HttpStatus.NOT_FOUND.value(), ex.getMessage(), LocalDateTime.now());
        logger.error("Project not found exception", ex.getMessage(), ex);
        return new ResponseEntity<ErrorObject>(errorObj, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProjectBadRequestException.class)
    public ResponseEntity<ErrorObject> HandleProjectBadRequestException(ProjectBadRequestException ex) {
        var errorObj = new ErrorObject(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), LocalDateTime.now());
        logger.error("Bad request exception", ex.getMessage(), ex);
        return new ResponseEntity<ErrorObject>(errorObj, HttpStatus.BAD_REQUEST);
    }
}
