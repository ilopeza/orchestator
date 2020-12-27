package com.spotify.orchestator.exceptions;

public class ClientException extends OrchestatorException {
    public ClientException(String message) {
        super(message);
    }

    public ClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
