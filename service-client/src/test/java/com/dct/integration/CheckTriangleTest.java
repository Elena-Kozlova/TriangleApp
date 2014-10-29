package com.dct.integration;

import com.dct.TestUtils;
import com.dct.core.data.Message;
import com.dct.service.request.TriangleRequest;
import com.dct.service.response.TriangleResponse;
import com.dct.service.response.VersionResponse;
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
        assertThat("Triangle request occurred with result: Error", triangleResponse.getErrorCode(), not(equalTo(0)));
    }

    @Test
    public void testGetApplicationVersion() throws IOException {
        ClientResponse clientResponse = restHttpClient.get(MediaType.APPLICATION_JSON_TYPE, TestUtils.getVersionTestApiUrl());
        Message<VersionResponse> versionResponse = clientResponse.getEntity(new GenericType<Message<VersionResponse>>() {});

        printTriangleResponse(versionResponse);
        assertThat("Get version request occurred with result: 1.0-SNAPSHOT", versionResponse.getData().getVersion(), is(equalTo("1.0-SNAPSHOT")));
    }

    protected void printTriangleResponse(Message response) {
        LOGGER.debug("Message response: {}", reflectionToString(response, ToStringStyle.SHORT_PREFIX_STYLE));
    }
}
