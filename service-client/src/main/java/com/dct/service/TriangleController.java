package com.dct.service;

import com.dct.client.TriangleClient;
import com.dct.core.data.Message;
import com.dct.service.request.TriangleRequest;
import com.dct.service.response.TriangleResponse;
import com.dct.service.response.VersionResponse;
import com.dct.http.HttpStatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/triangle")
public class TriangleController {

    @Autowired
    private TriangleClient triangleClient;

    private static final Logger LOGGER = LoggerFactory.getLogger(TriangleController.class);

    @RequestMapping(value = "/checkTriangle", method = RequestMethod.POST)
    public
    @ResponseBody
    Message<TriangleResponse> checkTriangle(@RequestBody TriangleRequest triangle) {
        Message<TriangleResponse> message = new Message<TriangleResponse>();

        try {
            TriangleResponse triangleResponse = triangleClient.checkTriangle(triangle);
            message.setData(triangleResponse);
        } catch (HttpStatusException e) {
            TriangleResponse triangleErrorResponse = e.getEntity(TriangleResponse.class);
            message.setMessage(triangleErrorResponse.getDetails());
            message.setError(e.getStatus());
        }

        return message;
    }

    @RequestMapping(value = "/version", method = RequestMethod.GET)
    public
    @ResponseBody
    Message<VersionResponse> getServiceVersion() {
        Message<VersionResponse> message = new Message<VersionResponse>();

        try {
            VersionResponse versionResponse = triangleClient.getServiceVersion();
            message.setData(versionResponse);
        } catch (HttpStatusException e) {
            VersionResponse versionErrorResponse = e.getEntity(VersionResponse.class);
            message.setMessage(versionErrorResponse.getDetails());
            message.setError(e.getStatus());
        }

        return message;
    }
}
