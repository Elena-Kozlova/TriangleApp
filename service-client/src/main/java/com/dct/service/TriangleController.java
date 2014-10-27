package com.dct.service;

import com.dct.service.request.TriangleRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/triangle")
public class TriangleController {

    @RequestMapping(value = "/checkTriangle", method = RequestMethod.POST)
    public
    @ResponseBody
    String checkTriangle(@RequestBody TriangleRequest triangle) {
        System.out.println("test");

        System.out.println(triangle.getA());
        System.out.println(triangle.getB());
        System.out.println(triangle.getC());

        return "test";
    }
}
