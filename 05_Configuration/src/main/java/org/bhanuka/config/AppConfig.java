package org.bhanuka.config;

import org.bhanuka.beans.E;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("org.bhanuka")
@Import({AppConfig1.class, Appconfig2.class})

/*
* we can use @Import to import another configuration/configurations
* and then we can add just this config to the spring context*/

public class AppConfig {
    @Bean
    public E e (){
        return new E();
    }
}
