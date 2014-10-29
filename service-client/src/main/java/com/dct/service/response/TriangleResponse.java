package com.dct.service.response;

import com.google.common.base.Objects;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TriangleResponse {

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
