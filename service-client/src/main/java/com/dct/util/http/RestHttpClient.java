package com.dct.util.http;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;

public class RestHttpClient<P> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestHttpClient.class);

    private final Client client;

    public RestHttpClient(Client client) {
        this.client = client;
    }

    public ClientResponse post(MediaType mediaType, P entityToPost, String targetURI) {
        WebResource.Builder builder = getBuilder(mediaType, targetURI);
        return builder.post(ClientResponse.class, entityToPost);
    }

    public ClientResponse get(MediaType mediaType, String targetURI) {
        WebResource.Builder builder = getBuilder(mediaType, targetURI);
        return builder.get(ClientResponse.class);
    }

    private WebResource.Builder getBuilder(MediaType mediaType, String targetURI) {
        WebResource webResource = client.resource(targetURI);
        WebResource.Builder builder = webResource.type(mediaType).accept(mediaType);

        LOGGER.info("Builder for TargetURI [{}] ", targetURI);

        return builder;
    }
}
