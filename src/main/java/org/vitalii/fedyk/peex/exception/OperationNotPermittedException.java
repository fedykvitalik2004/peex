package org.vitalii.fedyk.peex.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OperationNotPermittedException extends RuntimeException{
    private LocalDateTime dateTime;

    public OperationNotPermittedException(LocalDateTime dateTime, String message) {
        super(message);
        this.dateTime = dateTime;
    }

    public OperationNotPermittedException(String message) {
        super(message);
    }
}
