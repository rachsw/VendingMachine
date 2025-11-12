package org.example.exceptions;

public class SystemError extends RuntimeException {
    public SystemError(String message) {
        super(message);
    }
}
