package com.dct.exception;

import com.sun.jersey.api.client.ClientResponse;

public class HttpStatusException extends Exception {

    private final int status;
    private final ClientResponse clientResponse;

    public HttpStatusException(String message, int status, ClientResponse clientResponse) {
        super(message);
        this.status = status;
        this.clientResponse = clientResponse;
    }

    public int getStatus() {
        return status;
    }

    public <T> T getEntity(Class<T> entityResponseClass) {
        return clientResponse.getEntity(entityResponseClass);
    }
}
