package org.bhanuka;

import org.bhanuka.bean.Boy;
import org.bhanuka.congif.AppConfig;
import org.bhanuka.di.Test1;
import org.bhanuka.di.Test2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);

        context.refresh();

//        Boy boy = context.getBean(Boy.class);
//        boy.chatWithGirl();
        Test2 test2 = context.getBean(Test2.class);
        test2.test();
        context.registerShutdownHook();
    }
}