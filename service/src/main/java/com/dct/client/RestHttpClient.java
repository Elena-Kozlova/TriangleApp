package com.dct.client;

import com.dct.exception.HttpStatusException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * RestHttpClient - allows to perform REST calls.
 *
 * @param <P> Object type to be Posted.
 * @param <R> Object type expected as Return.
 *           <p/>
 *           Methods in this class return the expected instance or @see HttpStatusException.
 *           The exception allows to get HTTP response status and deserizlize response.
 */
public class RestHttpClient<P, R> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestHttpClient.class);

    private final Client client;

    public RestHttpClient(Client client) {
        this.client = client;
    }

    /**
     * POST method
     *
     * @throws HttpStatusException
     */
    public R post(MediaType mediaType, P entityToPost, String targetURI, Class<R> entityResponseClass) throws HttpStatusException {
        WebResource.Builder builder = getBuilder(mediaType, targetURI);
        ClientResponse response = builder.post(ClientResponse.class, entityToPost);
        return getResponse(entityResponseClass, response);
    }

    /**
     * Get method
     *
     * @throws HttpStatusException
     */
    public R get(MediaType mediaType, String targetURI, Class<R> entityResponseClass) throws HttpStatusException {
        WebResource.Builder builder = getBuilder(mediaType, targetURI);
        ClientResponse response = builder.get(ClientResponse.class);
        return getResponse(entityResponseClass, response);
    }

    /**
     * Creates a WebResource.Builder according to given parameters.
     */
    private WebResource.Builder getBuilder(MediaType mediaType, String targetURI) {
        WebResource webResource = client.resource(targetURI);
        WebResource.Builder builder = webResource.type(mediaType).accept(mediaType);

        LOGGER.info("Builder for TargetURI [{}] ", targetURI);

        return builder;
    }

    /**
     * Returns deserialized response or throws @see HttpStatusException if the response status is not 200.
     */
    private R getResponse(Class<R> entityResponseClass, ClientResponse response) throws HttpStatusException {
        int status = response.getStatus();
        LOGGER.info("Status [{}] ", status);
        if (status == Response.Status.OK.getStatusCode()) {
            return response.getEntity(entityResponseClass);
        } else {
            throw new HttpStatusException("Request unsuccessful", status, response);
        }
    }
}
