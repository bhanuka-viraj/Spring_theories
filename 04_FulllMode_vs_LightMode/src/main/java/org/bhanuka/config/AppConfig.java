package org.bhanuka.config;

import org.bhanuka.beans.SpringBeanOne;
import org.bhanuka.beans.SpringBeanTwo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.bhanuka")
public class AppConfig {

    @Bean
    public SpringBeanOne springBeanOne() {
        //******** Inter bean dependencies **************

//        ***cannot get the dependency like this- * not adding to the context so they won't be beans
//        SpringBeanTwo springBeanTwo1 = new SpringBeanTwo();
//        SpringBeanTwo springBeanTwo2 = new SpringBeanTwo();

        SpringBeanTwo springBeanTwo1 = springBeanTwo(); // adding to the context, and returning the created bean
        SpringBeanTwo springBeanTwo2 = springBeanTwo();

        System.out.println(springBeanTwo1);
        System.out.println(springBeanTwo2);

        return new SpringBeanOne();
    }

    @Bean
    public SpringBeanTwo springBeanTwo(){
        return new SpringBeanTwo();
    }
}
