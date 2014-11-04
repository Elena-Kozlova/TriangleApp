package com.dct.service.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseErrorResponse {

    private String message;
    private String details;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("message", message)
                .add("details", details)

                .toString();
    }
}
