package org.bhanuka;

import org.bhanuka.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

//--------------- System variables --------------------------
//        Map<String, String> env = System.getenv();

//        for (Map.Entry<String, String> entry : env.entrySet()) {
//            System.out.println(entry.getKey() + " : " + entry.getValue());
//        }

//        System.out.println(env.get("JAVA_HOME"));



//--------------- Predefined java properties --------------------------
        //System.getProperties().list(System.out);
        //Object version = System.getProperties().get("java.specification.version");
        //System.out.println(version);


        context.registerShutdownHook();
    }
}