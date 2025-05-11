package org.example.test_task.exception.subscribe;

import org.springframework.http.HttpStatus;

public class SubscribeCreateException extends SubscribeException {
    public SubscribeCreateException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
