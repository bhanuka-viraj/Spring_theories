package org.bhanuka.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("org.bhanuka")
@PropertySource({"classpath:application.properties"}) //need to give the path of the property file
public class AppConfig {
}
