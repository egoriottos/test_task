package org.example.test_task.exception.user;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends UserException {
    public UserNotFoundException(Long userId) {
        super("User not found with ID: " + userId, HttpStatus.NOT_FOUND);
    }
}
