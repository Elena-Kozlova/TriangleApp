package com.dct.client;

import com.dct.config.TriangleHttpUrls;
import com.dct.exception.HttpStatusException;
import com.dct.service.request.TriangleRequest;
import com.dct.service.response.TriangleResponse;
import com.dct.service.response.VersionResponse;
import com.sun.jersey.api.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.MediaType;

@Component
public class TriangleClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(TriangleClient.class);

    @Value("${triangle.api.url}")
    private String triangleApiUrl;

    private RestHttpClient httpClient;

    public TriangleClient() {
        Client client = Client.create();
        httpClient = new RestHttpClient(client);
    }

    public TriangleResponse checkTriangle(TriangleRequest triangleRequest) throws HttpStatusException {
        LOGGER.info("Posting triangle request: {}", triangleRequest);

        TriangleResponse triangleResponse = (TriangleResponse) httpClient.post(MediaType.APPLICATION_JSON_TYPE, triangleRequest,
                triangleApiUrl + TriangleHttpUrls.CHECK_TRIANGLE_SERVICE, TriangleResponse.class);

        LOGGER.debug("Triangle response: {}", triangleResponse);

        return triangleResponse;
    }

    public VersionResponse getServiceVersion() throws HttpStatusException {
        LOGGER.info("Getting service version");

        VersionResponse versionResponse = (VersionResponse) httpClient.get(MediaType.APPLICATION_JSON_TYPE,
                triangleApiUrl + TriangleHttpUrls.GET_TRIANGLE_SERVICE_VERSION, VersionResponse.class);

        LOGGER.debug("Triangle version response: {}", versionResponse);

        return versionResponse;
    }
}
