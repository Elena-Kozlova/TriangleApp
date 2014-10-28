package com.dct.service.request;

public class CheckTriangleRequest
{
    private Double a;
    private Double b;
    private Double c;

    public CheckTriangleRequest() {}

    public CheckTriangleRequest(Double a, Double b, Double c)
    {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Double getA()
    {
        return this.a;
    }

    public void setA(Double a)
    {
        this.a = a;
    }

    public Double getB()
    {
        return this.b;
    }

    public void setB(Double b)
    {
        this.b = b;
    }

    public Double getC()
    {
        return this.c;
    }

    public void setC(Double c)
    {
        this.c = c;
    }

    public String toString()
    {
        return String.format("a=%f, b=%f, c=%f", new Object[] { this.a, this.b, this.c });
    }
}
