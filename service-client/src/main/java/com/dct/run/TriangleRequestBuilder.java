package com.dct.run;

import com.dct.service.request.TriangleRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Builder of triangle request.
 */
public class TriangleRequestBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(TriangleRequestBuilder.class);

    /**
     * Builds TriangleRequest from the data entered from the keyboard.
     *
     * @return TriangleRequest object
     * @throws IOException
     */
    public TriangleRequest buildTriangleRequest() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter side A:");
        String a = br.readLine();

        System.out.println("Enter side B:");
        String b = br.readLine();

        System.out.println("Enter side C:");
        String c = br.readLine();

        return composeTriangleRequest(a, b, c);
    }

    /**
     * Builds TriangleRequest object from the data provided in the arguments.
     *
     * @param args arguments
     * @return TriangleRequest object
     */
    public TriangleRequest buildTriangleRequest(String[] args) {
        return composeTriangleRequest(args[0], args[1], args[2]);
    }

    /**
     * Builds TriangleRequest from the 3 parameters (sides of triangle).
     *
     * @param a Side A.
     * @param b Side B.
     * @param c Side C.
     * @return TriangleRequest object
     */
    private TriangleRequest composeTriangleRequest(String a, String b, String c) {
        TriangleRequest triangleRequest = new TriangleRequest();

        try {
            triangleRequest.setA(Double.parseDouble(a));
            triangleRequest.setB(Double.parseDouble(b));
            triangleRequest.setC(Double.parseDouble(c));

            LOGGER.info("Triangle request composed: {}", triangleRequest);
        }
        catch (NumberFormatException e) {
            System.out.println("The side(s) is in incorrect format");
            throw e;
        }

        return triangleRequest;
    }
}
