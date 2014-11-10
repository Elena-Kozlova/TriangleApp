package com.dct.service;

import com.dct.client.TriangleClient;
import com.dct.core.data.Message;
import com.dct.service.request.TriangleRequest;
import com.dct.service.response.TriangleResponse;
import com.dct.service.response.VersionResponse;
import com.dct.exception.HttpStatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/triangle")
public class TriangleController extends BaseController {

    @Autowired
    private TriangleClient triangleClient;

    private static final Logger LOGGER = LoggerFactory.getLogger(TriangleController.class);

    @RequestMapping(value = "/checkTriangle", method = RequestMethod.POST)
    public
    @ResponseBody
    Message checkTriangle(@RequestBody TriangleRequest triangle) {
        try {
            Message<TriangleResponse> message = new Message<TriangleResponse>();
            TriangleResponse triangleResponse = triangleClient.checkTriangle(triangle);
            message.setData(triangleResponse);
            return message;
        } catch (HttpStatusException e) {
            return getErrorResponse(e);
        }
    }

    @RequestMapping(value = "/version", method = RequestMethod.GET)
    public
    @ResponseBody
    Message getServiceVersion() {
        try {
            Message<VersionResponse> message = new Message<VersionResponse>();
            VersionResponse versionResponse = triangleClient.getServiceVersion();
            message.setData(versionResponse);
            return message;
        } catch (HttpStatusException e) {
            return getErrorResponse(e);
        }
    }
}
