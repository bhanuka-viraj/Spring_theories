package org.bhanuka;

import org.bhanuka.bean.MyConnection;
import org.bhanuka.bean.TestBean;
import org.bhanuka.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();

        TestBean t1 = context.getBean(TestBean.class);

        TestBean t2 = context.getBean(TestBean.class);
//        System.out.println(t1);
//        System.out.println(t2);

        MyConnection m1 = context.getBean(MyConnection.class);
        System.out.println(m1);

        context.registerShutdownHook(); // Registers a JVM shutdown hook to automatically close the ApplicationContext when the JVM terminates (e.g., on program exit or Ctrl+C).
//        context.close();  //Explicitly closes the ApplicationContext, shutting it down immediately and releasing resources.

    }
}