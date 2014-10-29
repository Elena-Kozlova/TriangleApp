package com.dct;

import com.dct.service.request.TriangleRequest;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class TestUtils {

    public static String getCheckTriangleTestApiUrl() throws IOException {
        Properties testProperties = getTestProperties();

        return testProperties.getProperty("checkTriangleTestApiURL");
    }

    public static String getVersionTestApiUrl() throws IOException {
        Properties testProperties = getTestProperties();

        return testProperties.getProperty("getVersionTestApiURL");
    }

    private static Properties getTestProperties() throws IOException {
        Properties props = new Properties();
        File propertyFile = getResourceFile("test.properties");
        props.load(new FileInputStream(propertyFile));
        return props;
    }

    private static File getResourceFile(String path) {
        URL resource = TestUtils.class.getClassLoader().getResource(path);
        return resource != null ? new File(resource.getFile()) : null;
    }

    private static TriangleRequest getRequestFromFile(String fileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JaxbAnnotationModule());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        InputStream orderDataFile = TestUtils.class.getClassLoader().getResourceAsStream(fileName);
        return mapper.readValue(orderDataFile, TriangleRequest.class);
    }

    public static TriangleRequest getCheckTriangleRequestYes() throws IOException {
        return getRequestFromFile("UnitTests/CheckTriangleRequestYes.json");
    }

    public static TriangleRequest getCheckTriangleRequestNo() throws IOException {
        return getRequestFromFile("UnitTests/CheckTriangleRequestNo.json");
    }

    public static TriangleRequest getCheckTriangleRequestError() throws IOException {
        return getRequestFromFile("UnitTests/CheckTriangleRequestError.json");
    }
}
