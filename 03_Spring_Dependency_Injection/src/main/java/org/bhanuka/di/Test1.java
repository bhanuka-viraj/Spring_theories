package org.bhanuka.di;

import org.springframework.stereotype.Component;

@Component
public class Test1 implements DI{
    public Test1(){
        System.out.println("Test1 created..");
    }

    @Override
    public void sayHello() {
        System.out.println("Hello... Test1");
    }
}
