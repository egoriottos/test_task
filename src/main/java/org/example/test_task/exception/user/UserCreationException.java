package org.example.test_task.exception.user;

import lombok.Getter;
import org.springframework.http.HttpStatus;
public class UserCreationException extends UserException {
    public UserCreationException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
