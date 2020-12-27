package com.spotify.orchestator.exceptions;

import org.springframework.web.reactive.function.client.ClientResponse;

public class MusicDetectionServiceException extends OrchestatorException {

    final ClientResponse response;

    public MusicDetectionServiceException(ClientResponse response) {
        super("null");
        this.response = response;
    }

    public MusicDetectionServiceException(String message, ClientResponse response) {
        super(message);
        this.response = response;
    }

    public MusicDetectionServiceException(String message, Throwable cause, ClientResponse response) {
        super(message, cause);
        this.response = response;
    }
}
