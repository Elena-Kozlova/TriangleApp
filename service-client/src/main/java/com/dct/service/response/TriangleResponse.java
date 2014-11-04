package com.dct.service.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TriangleResponse extends BaseErrorResponse {

    private String exists;

    public String getExists() {
        return exists;
    }

    public void setExists(String exists) {
        this.exists = exists;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("exists", exists)

                .toString();
    }
}
