package com.dct.integration;

import com.dct.IntegrationTests;
import com.dct.TestUtils;
import com.dct.client.TriangleClient;
import com.dct.service.request.TriangleRequest;
import com.dct.service.response.BaseErrorResponse;
import com.dct.service.response.TriangleExistsEnum;
import com.dct.service.response.TriangleResponse;
import com.dct.service.response.VersionResponse;
import com.dct.exception.HttpStatusException;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@ContextConfiguration(locations = {"classpath:/web-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Category(IntegrationTests.class)
public class CheckTriangleTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CheckTriangleTest.class);

    @InjectMocks
    @Autowired
    private TriangleClient triangleClient;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCheckTriangleRequestYes() throws IOException, HttpStatusException {
        TriangleRequest triangleRequest = TestUtils.getCheckTriangleRequestYes();
        TriangleResponse triangleResponse = triangleClient.checkTriangle(triangleRequest);

        printTriangleResponse(triangleResponse);
        assertThat("Triangle request occurred with result: YES", triangleResponse.getExists(), is(equalTo(TriangleExistsEnum.YES)));
    }

    @Test
    public void testCheckTriangleRequestNo() throws IOException, HttpStatusException {
        TriangleRequest triangleRequest = TestUtils.getCheckTriangleRequestNo();
        TriangleResponse triangleResponse = triangleClient.checkTriangle(triangleRequest);

        printTriangleResponse(triangleResponse);
        assertThat("Triangle request occurred with result: NO", triangleResponse.getExists(), is(equalTo(TriangleExistsEnum.NO)));
    }

    @Test(expected = HttpStatusException.class)
    public void testCheckTriangleRequestError() throws IOException, HttpStatusException {
        TriangleRequest triangleRequest = TestUtils.getCheckTriangleRequestError();
        triangleClient.checkTriangle(triangleRequest);
    }

    @Test
    public void testGetApplicationVersion() throws IOException, HttpStatusException {
        VersionResponse versionResponse = triangleClient.getServiceVersion();
        printTriangleResponse(versionResponse);
        assertThat("Get version request occurred with result: 1.0-SNAPSHOT", versionResponse.getVersion(), is(equalTo("1.0-SNAPSHOT")));
    }

    protected void printTriangleResponse(BaseErrorResponse baseErrorResponse) {
        LOGGER.debug("Triangle response: {}", reflectionToString(baseErrorResponse, ToStringStyle.SHORT_PREFIX_STYLE));
    }
}
