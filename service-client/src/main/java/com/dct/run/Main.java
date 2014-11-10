package com.dct.run;

import com.dct.client.TriangleClient;
import com.dct.exception.HttpStatusException;
import com.dct.service.request.TriangleRequest;
import com.dct.service.response.TriangleExistsEnum;
import com.dct.service.response.TriangleResponse;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        TriangleRequestBuilder requestBuilder = new TriangleRequestBuilder();
        TriangleRequest triangleRequest;

        if (args.length != 3) {
            triangleRequest = requestBuilder.buildTriangleRequest();
        }
        else {
            triangleRequest = requestBuilder.buildTriangleRequest(args);
        }

        TriangleContextLoader contextLoader = new TriangleContextLoader();
        TriangleClient triangleClient = contextLoader.getTriangleClient();

        try {
            TriangleResponse triangleResponse = triangleClient.checkTriangle(triangleRequest);

            if (triangleResponse.getExists() == TriangleExistsEnum.YES) {
                System.out.println("Triangle exists!");
            } else if (triangleResponse.getExists() == TriangleExistsEnum.NO) {
                System.out.println("Triangle doesn't exist!");
            }
        }
        catch (HttpStatusException e) {
            System.out.println(e.getMessage());
        }
    }
}
