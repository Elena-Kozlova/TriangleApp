package com.dct.service;

import com.dct.config.TriangleHttpUrls;
import com.dct.core.data.Message;
import com.dct.service.request.TriangleRequest;
import com.dct.service.response.ErrorResponse;
import com.dct.service.response.TriangleResponse;
import com.dct.service.response.VersionResponse;
import com.dct.util.http.RestHttpClient;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.core.MediaType;

@Controller
@RequestMapping("/triangle")
public class TriangleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TriangleController.class);

    @Value("${triangle.api.url}")
    private String triangleApiUrl;

    @RequestMapping(value = "/checkTriangle", method = RequestMethod.POST)
    public
    @ResponseBody
    Message<TriangleResponse> checkTriangle(@RequestBody TriangleRequest triangle) {
        LOGGER.info("Posting triangle request: {}", triangle);

        Client client = Client.create();

        RestHttpClient<TriangleRequest> httpClient = new RestHttpClient<TriangleRequest>(client);
        ClientResponse response = httpClient.post(MediaType.APPLICATION_JSON_TYPE, triangle, triangleApiUrl + TriangleHttpUrls.CHECK_TRIANGLE_SERVICE);

        Message<TriangleResponse> message = new Message<TriangleResponse>();

        LOGGER.debug("Triangle response: {}", response);

        if (response.getStatus() != 200) {
            ErrorResponse errorResponse = response.getEntity(ErrorResponse.class);

            message.setError(response.getStatus());
            message.setMessage(errorResponse.getDetails());
        }
        else {
            TriangleResponse triangleResponse = response.getEntity(TriangleResponse.class);
            message.setData(triangleResponse);
        }

        return message;
    }

    @RequestMapping(value = "/version", method = RequestMethod.GET)
    public
    @ResponseBody
    Message<VersionResponse> getServiceVersion() {
        LOGGER.info("Getting service version");

        Client client = Client.create();

        RestHttpClient httpClient = new RestHttpClient(client);
        ClientResponse response = httpClient.get(MediaType.APPLICATION_JSON_TYPE, triangleApiUrl + TriangleHttpUrls.GET_TRIANGLE_SERVICE_VERSION);

        Message<VersionResponse> message = new Message<VersionResponse>();

        LOGGER.debug("Triangle version response: {}", response);

        if (response.getStatus() != 200) {
            ErrorResponse errorResponse = response.getEntity(ErrorResponse.class);

            message.setError(response.getStatus());
            message.setMessage(errorResponse.getDetails());
        }
        else {
            VersionResponse triangleVersionResponse = response.getEntity(VersionResponse.class);
            message.setData(triangleVersionResponse);
        }

        return message;
    }
}
