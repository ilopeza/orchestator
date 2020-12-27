package com.spotify.orchestator.exceptions;

public class MissingConfigurationException extends OrchestatorException {

    public MissingConfigurationException(String message) {
        super(message);
    }

    public MissingConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}
