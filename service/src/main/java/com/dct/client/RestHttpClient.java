package com.dct.client;

import com.dct.exception.HttpStatusException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestHttpClient<P, R> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestHttpClient.class);

    private final Client client;

    public RestHttpClient(Client client) {
        this.client = client;
    }

    public R post(MediaType mediaType, P entityToPost, String targetURI, Class<R> entityResponseClass) throws HttpStatusException {
        WebResource.Builder builder = getBuilder(mediaType, targetURI);
        ClientResponse response = builder.post(ClientResponse.class, entityToPost);
        return getResponse(entityResponseClass, response);
    }

    public R post(MediaType mediaType, P entityToPost, String targetURI, GenericType<R> entityResponseClass) throws HttpStatusException {
        WebResource.Builder builder = getBuilder(mediaType, targetURI);
        ClientResponse response = builder.post(ClientResponse.class, entityToPost);
        return getResponse(entityResponseClass, response);
    }

    public R get(MediaType mediaType, String targetURI, Class<R> entityResponseClass) throws HttpStatusException {
        WebResource.Builder builder = getBuilder(mediaType, targetURI);
        ClientResponse response = builder.get(ClientResponse.class);
        return getResponse(entityResponseClass, response);
    }

    public R get(MediaType mediaType, String targetURI, GenericType<R> entityResponseClass) throws HttpStatusException {
        WebResource.Builder builder = getBuilder(mediaType, targetURI);
        ClientResponse response = builder.get(ClientResponse.class);
        return getResponse(entityResponseClass, response);
    }

    private WebResource.Builder getBuilder(MediaType mediaType, String targetURI) {
        WebResource webResource = client.resource(targetURI);
        WebResource.Builder builder = webResource.type(mediaType).accept(mediaType);

        LOGGER.info("Builder for TargetURI [{}] ", targetURI);

        return builder;
    }

    private R getResponse(Class<R> entityResponseClass, ClientResponse response) throws HttpStatusException {
        int status = response.getStatus();
        LOGGER.info("Status [{}] ", status);
        if (status == Response.Status.OK.getStatusCode() || status == Response.Status.CREATED.getStatusCode()) {
            return response.getEntity(entityResponseClass);
        } else {
            throw new HttpStatusException("Request unsuccessful", status, response);
        }
    }

    private R getResponse(GenericType<R> entityResponseClass, ClientResponse response) throws HttpStatusException {
        int status = response.getStatus();
        LOGGER.info("Status [{}] ", status);
        if (status == Response.Status.OK.getStatusCode() || status == Response.Status.CREATED.getStatusCode()) {
            return response.getEntity(entityResponseClass);
        } else {
            throw new HttpStatusException("Request unsuccessful", status, response);
        }
    }
}
