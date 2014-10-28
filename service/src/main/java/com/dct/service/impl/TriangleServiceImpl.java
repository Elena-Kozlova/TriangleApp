package com.dct.service.impl;

import com.dct.service.TriangleService;
import com.dct.service.request.CheckTriangleRequest;
import org.springframework.stereotype.Service;

@Service("triangleService")
public class TriangleServiceImpl implements TriangleService {

    public TriangleServiceImpl() {}

    public Boolean checkTriangle(CheckTriangleRequest request)
            throws IllegalArgumentException
    {
        if ((request.getA() == null) || (request.getB() == null) || (request.getC() == null)) {
            throw new IllegalArgumentException("Side length cannot be empty");
        }
        return Boolean.valueOf((request.getA().doubleValue() < request.getB().doubleValue() + request.getC().doubleValue()) &&
                (request.getB().doubleValue() < request.getC().doubleValue() + request.getA().doubleValue()) &&
                (request.getC().doubleValue() < request.getA().doubleValue() + request.getB().doubleValue()));
    }
}
