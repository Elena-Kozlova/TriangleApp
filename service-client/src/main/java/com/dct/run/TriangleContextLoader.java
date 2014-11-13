package com.dct.run;

import com.dct.client.TriangleClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring context loader.
 */
public class TriangleContextLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(TriangleContextLoader.class);

    private TriangleClient triangleClient;

    public TriangleContextLoader() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("./web-context.xml");
        triangleClient = applicationContext.getBean(TriangleClient.class);
        LOGGER.info("Spring context initialized: {}", applicationContext);
    }

    public TriangleClient getTriangleClient() {
        return triangleClient;
    }
}
