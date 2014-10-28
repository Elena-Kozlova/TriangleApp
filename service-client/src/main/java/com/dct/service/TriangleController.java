package com.dct.service;

import com.dct.config.TriangleHttpUrls;
import com.dct.core.data.Message;
import com.dct.service.request.TriangleRequest;
import com.dct.service.response.ErrorResponse;
import com.dct.service.response.TriangleResponse;
import com.dct.util.http.RestHttpClient;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
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

    @Value("${triangle.api.url}")
    private String triangleApiUrl;

    @RequestMapping(value = "/checkTriangle", method = RequestMethod.POST)
    public
    @ResponseBody
    Message<TriangleResponse> checkTriangle(@RequestBody TriangleRequest triangle) throws Exception {
        Client client = Client.create();

        RestHttpClient<TriangleRequest> httpClient = new RestHttpClient<TriangleRequest>(client);
        ClientResponse response = httpClient.post(MediaType.APPLICATION_JSON_TYPE, triangle, triangleApiUrl + TriangleHttpUrls.CHECK_TRIANGLE_SERVICE);

        Message<TriangleResponse> message = new Message<TriangleResponse>();

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
}
