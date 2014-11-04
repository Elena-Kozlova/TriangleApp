package com.dct.service.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VersionResponse extends BaseErrorResponse {

    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("version", version)

                .toString();
    }
}
