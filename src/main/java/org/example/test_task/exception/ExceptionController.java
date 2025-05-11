package org.example.test_task.exception;

import lombok.extern.slf4j.Slf4j;
import org.example.test_task.exception.subscribe.SubscribeCreateException;
import org.example.test_task.exception.subscribe.SubscribeNotFoundException;
import org.example.test_task.exception.user.UserCreationException;
import org.example.test_task.exception.user.UserException;
import org.example.test_task.exception.user.UserNotFoundException;
import org.example.test_task.exception.user.UserUpdateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionController {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> handleCommonException(Exception exception) {
        log.error("Cause: {}, trace: {}", exception.getCause(), exception.getStackTrace());
        return new ResponseEntity<>("Server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({UserNotFoundException.class, SubscribeNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException e) {
        log.warn("Message: {}",e.getMessage());
        return new ResponseEntity<>(e.getMessage(),e.getStatus());
    }

    @ExceptionHandler({UserCreationException.class, UserUpdateException.class, SubscribeCreateException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleUserOperationsException(UserException ex) {
        log.warn("Operation failed: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
    }
}
