package org.bhanuka;

import org.bhanuka.beans.*;
import org.bhanuka.config.AppConfig;
import org.bhanuka.config.AppConfig1;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        A a = context.getBean(A.class);
        System.out.println(a);
        B b = context.getBean(B.class);
        System.out.println(b);
        C c = context.getBean(C.class);
        System.out.println(c);
        D d = context.getBean(D.class);
        System.out.println(d);
        E e = context.getBean(E.class);
        System.out.println(e);

        context.registerShutdownHook();
    }
}