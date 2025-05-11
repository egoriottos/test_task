package org.example.test_task.exception.user;

import org.springframework.http.HttpStatus;
public class UserUpdateException extends UserException {
    public UserUpdateException(String message) {
        super(message,HttpStatus.BAD_REQUEST);
    }
}
