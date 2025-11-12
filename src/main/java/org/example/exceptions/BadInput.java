package org.example.exceptions;

public class BadInput extends RuntimeException {
    public BadInput(String message) {
        super(message);
    }
}
