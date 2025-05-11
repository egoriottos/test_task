package org.example.test_task.exception.subscribe;

import org.springframework.http.HttpStatus;

public class SubscribeNotFoundException extends SubscribeException {
    public SubscribeNotFoundException(Long subId) {
        super("Subscribe not found with ID: "+subId, HttpStatus.NOT_FOUND);
    }
}
