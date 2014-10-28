package com.dct.service;

import com.dct.service.request.CheckTriangleRequest;

public abstract interface TriangleService
{
    public abstract Boolean checkTriangle(CheckTriangleRequest paramCheckTriangleRequest)
            throws IllegalArgumentException;
}
