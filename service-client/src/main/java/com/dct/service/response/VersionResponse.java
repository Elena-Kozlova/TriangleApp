package com.dct.service.response;

import com.google.common.base.Objects;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class VersionResponse {

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
