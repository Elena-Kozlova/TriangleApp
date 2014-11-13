package com.dct.service.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Objects;

/**
 *  TriangleResponse object - contains result of triangle checking.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TriangleResponse extends BaseErrorResponse {

    private TriangleExistsEnum exists;

    public TriangleExistsEnum getExists() {
        return exists;
    }

    public void setExists(TriangleExistsEnum exists) {
        this.exists = exists;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("exists", exists)

                .toString();
    }
}
