package org.example.exceptions;

public class DoesNotExist extends RuntimeException {
    public DoesNotExist(String message) {
        super(message);
    }
}
