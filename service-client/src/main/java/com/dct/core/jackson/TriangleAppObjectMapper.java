package com.dct.core.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TriangleAppObjectMapper extends ObjectMapper {

    public TriangleAppObjectMapper() {
        setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    }
}
