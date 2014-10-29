package com.dct.run;

import com.dct.core.data.Message;
import com.dct.service.TriangleController;
import com.dct.service.request.TriangleRequest;
import com.dct.service.response.TriangleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TriangleClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(TriangleClient.class);

    private TriangleController triangleController;

    public TriangleClient() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("./web-context.xml");
        triangleController = applicationContext.getBean(TriangleController.class);

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
        Message<TriangleResponse> responseMessage = triangleController.checkTriangle(triangleRequest);

        if(responseMessage.getErrorCode() == 0) {
            if(responseMessage.getData().getExists().equalsIgnoreCase("YES")) {
                System.out.println("Triangle exists!");
            }
            else if(responseMessage.getData().getExists().equalsIgnoreCase("NO")) {
                System.out.println("Triangle doesn't exist!");
            }
        }
        else {
            System.out.printf(responseMessage.getMessage());
        }
    }
}
