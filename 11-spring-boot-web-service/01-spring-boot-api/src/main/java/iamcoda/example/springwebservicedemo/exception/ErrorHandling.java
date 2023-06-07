package iamcoda.example.springwebservicedemo.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ErrorHandling {
    private LocalDateTime timestamp;

    private String message;

    private String details;

    public ErrorHandling(LocalDateTime timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
