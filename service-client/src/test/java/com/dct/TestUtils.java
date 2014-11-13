package com.dct;

import com.dct.service.request.TriangleRequest;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

import java.io.IOException;
import java.io.InputStream;

public class TestUtils {

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
