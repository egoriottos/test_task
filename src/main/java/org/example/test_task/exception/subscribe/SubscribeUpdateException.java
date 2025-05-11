package org.example.test_task.exception.subscribe;

import org.springframework.http.HttpStatus;

public class SubscribeUpdateException extends SubscribeException {
    public SubscribeUpdateException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
