package com.dct.integration;

import com.dct.TestUtils;
import com.dct.core.data.Message;
import com.dct.service.request.TriangleRequest;
import com.dct.service.response.TriangleResponse;
import com.dct.util.http.RestHttpClient;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import java.io.IOException;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class CheckTriangleTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CheckTriangleTest.class);

    private RestHttpClient<TriangleRequest> restHttpClient;

    @Before
    public void setUp() {
        Client client = Client.create();
        restHttpClient = new RestHttpClient<TriangleRequest>(client);
    }

    @Test
    public void testCheckTriangleRequestYes() throws IOException {
        TriangleRequest triangleRequest = TestUtils.getCheckTriangleRequestYes();

        ClientResponse clientResponse = restHttpClient.post(MediaType.APPLICATION_JSON_TYPE, triangleRequest, TestUtils.getCheckTriangleTestApiUrl());
        Message<TriangleResponse> triangleResponse = clientResponse.getEntity(new GenericType<Message<TriangleResponse>>() {});

        printTriangleResponse(triangleResponse);
        assertThat("Triangle request occurred with result: YES", triangleResponse.getData().getExists(), is(equalTo("YES")));
    }

    @Test
    public void testCheckTriangleRequestNo() throws IOException {
        TriangleRequest triangleRequest = TestUtils.getCheckTriangleRequestNo();

        ClientResponse clientResponse = restHttpClient.post(MediaType.APPLICATION_JSON_TYPE, triangleRequest, TestUtils.getCheckTriangleTestApiUrl());
        Message<TriangleResponse> triangleResponse = clientResponse.getEntity(new GenericType<Message<TriangleResponse>>() {});

        printTriangleResponse(triangleResponse);
        assertThat("Triangle request occurred with result: NO", triangleResponse.getData().getExists(), is(equalTo("NO")));
    }

    @Test
    public void testCheckTriangleRequestError() throws IOException {
        TriangleRequest triangleRequest = TestUtils.getCheckTriangleRequestError();

        ClientResponse clientResponse = restHttpClient.post(MediaType.APPLICATION_JSON_TYPE, triangleRequest, TestUtils.getCheckTriangleTestApiUrl());
        Message<TriangleResponse> triangleResponse = clientResponse.getEntity(new GenericType<Message<TriangleResponse>>() {});

        printTriangleResponse(triangleResponse);
        assertThat("Triangle request occurred with result: NO", triangleResponse.getErrorCode(), not(equalTo(0)));
    }

    protected void printTriangleResponse(Message<TriangleResponse> triangleResponse) {
        LOGGER.debug("Message response: {}", reflectionToString(triangleResponse, ToStringStyle.SHORT_PREFIX_STYLE));
        LOGGER.debug("Triangle response: {}", reflectionToString(triangleResponse.getData(), ToStringStyle.SHORT_PREFIX_STYLE));
    }
}
