package org.bhanuka.config;

import org.bhanuka.beans.C;
import org.bhanuka.beans.D;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.bhanuka")
public class Appconfig2 {

    @Bean
    public C c (){
        return new C();
    }

    @Bean
    public D d (){
        return new D();
    }
}
