package com.dct.exception;

import com.sun.jersey.api.client.ClientResponse;

/**
 *  Exception when unexpected HTTP status response occurred
 */
public class HttpStatusException extends Exception {

    private final int status;
    private final ClientResponse clientResponse;

    /**
     * HttpStatusException constructor.
     *
     * @param message        Generic message.
     * @param status         HTTP status.
     * @param clientResponse The actual response from the HTTP call.
     */
    public HttpStatusException(String message, int status, ClientResponse clientResponse) {
        super(message);
        this.status = status;
        this.clientResponse = clientResponse;
    }

    /**
     *
     * @return HTTP status
     */
    public int getStatus() {
        return status;
    }

    /**
     * Deserializes respone into an object.
     *
     * @param entityResponseClass<T> The class representing the expected response.
     * @return The instance of type <T> with the response content.
     */
    public <T> T getEntity(Class<T> entityResponseClass) {
        return clientResponse.getEntity(entityResponseClass);
    }
}
