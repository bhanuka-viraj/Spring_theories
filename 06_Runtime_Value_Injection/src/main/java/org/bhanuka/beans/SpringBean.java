package org.bhanuka.beans;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SpringBean implements InitializingBean {
    // value injection - instance properties
    @Value("Bhanuka")
    String name;

    @Value("kuendn@344")
    String key;

    public SpringBean () {
        System.out.println("Spring bean key value is: " + key);
        System.out.println("Spring bean name value is: " + name);
        // will be null because the name is not set in the initializing phase of the lifecycle
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Spring bean key value is: " + key);
        System.out.println("Spring bean name value is: " + name);
        // will print the name and key
    }
}
