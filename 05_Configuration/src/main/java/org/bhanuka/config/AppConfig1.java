package org.bhanuka.config;

import org.bhanuka.beans.A;
import org.bhanuka.beans.B;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.bhanuka")
public class AppConfig1 {

    @Bean
    public A a(){
        return new A();
    }

    @Bean
    public B b(){
        return new B();
    }
}
