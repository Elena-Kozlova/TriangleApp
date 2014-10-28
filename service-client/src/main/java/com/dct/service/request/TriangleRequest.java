package com.dct.service.request;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TriangleRequest {
    private Double a;
    private Double b;
    private Double c;

    public Double getA() {
        return a;
    }

    public void setA(Double a) {
        this.a = a;
    }

    public Double getB() {
        return b;
    }

    public void setB(Double b) {
        this.b = b;
    }

    public Double getC() {
        return c;
    }

    public void setC(Double c) {
        this.c = c;
    }
}

