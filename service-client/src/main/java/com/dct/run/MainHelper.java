package com.dct.run;

import com.dct.client.TriangleClient;
import com.dct.exception.HttpStatusException;
import com.dct.service.request.TriangleRequest;
import com.dct.service.response.TriangleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainHelper.class);

    private TriangleClient triangleClient;

    public MainHelper() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("./web-context.xml");
        triangleClient = applicationContext.getBean(TriangleClient.class);
        LOGGER.info("Spring context initialized: {}", applicationContext);
    }

    public TriangleRequest composeTriangleRequest(String a, String b, String c) {
        TriangleRequest triangleRequest = new TriangleRequest();

        triangleRequest.setA(Double.parseDouble(a));
        triangleRequest.setB(Double.parseDouble(b));
        triangleRequest.setC(Double.parseDouble(c));

        LOGGER.info("Triangle request composed: {}", triangleRequest);

        return triangleRequest;
    }

    public void checkTriangle(TriangleRequest triangleRequest) {
        try {
            TriangleResponse triangleResponse = triangleClient.checkTriangle(triangleRequest);

            if (triangleResponse.getExists().equalsIgnoreCase("YES")) {
                System.out.println("Triangle exists!");
            } else if (triangleResponse.getExists().equalsIgnoreCase("NO")) {
                System.out.println("Triangle doesn't exist!");
            }
        }
        catch (HttpStatusException e) {
            System.out.println(e.getMessage());
        }
    }
}
