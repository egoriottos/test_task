package org.example.test_task.exception.subscribe;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SubscribeException extends RuntimeException {
  private final HttpStatus status;
    public SubscribeException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
