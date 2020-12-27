package com.spotify.orchestator.exceptions;

public class OrchestatorException extends RuntimeException {
    public OrchestatorException(String message) {
        super(message);
    }

    public OrchestatorException(String message, Throwable cause) {
        super(message, cause);
    }
}
