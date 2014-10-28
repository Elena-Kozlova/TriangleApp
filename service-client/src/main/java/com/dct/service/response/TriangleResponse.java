package com.dct.service.response;

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
}
