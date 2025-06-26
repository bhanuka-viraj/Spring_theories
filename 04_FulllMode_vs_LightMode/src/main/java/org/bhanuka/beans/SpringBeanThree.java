package org.bhanuka.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SpringBeanThree {
    // cannot use interbean dependencies in light mode,
    /*
    * if the bean methods defined in another bean like this it's called **light mode

    * */

    @Bean
    public SpringBeanOne springBeanOne() {
        //******** Inter bean dependencies **************

        SpringBeanTwo springBeanTwo1 = springBeanTwo();
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
