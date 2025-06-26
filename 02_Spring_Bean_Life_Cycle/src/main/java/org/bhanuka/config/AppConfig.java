package org.bhanuka.config;

import org.bhanuka.bean.MyConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("org.bhanuka.bean")
public class AppConfig {

    @Bean
    @Scope("prototype")
    MyConnection myConnection(){
        return new MyConnection();
    }
}
