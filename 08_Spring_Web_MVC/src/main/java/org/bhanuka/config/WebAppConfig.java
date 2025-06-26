package org.bhanuka.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan({"org.bhanuka.bean","org.bhanuka.controller"})
@EnableWebMvc
public class WebAppConfig {

}
