package org.bhanuka.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Test2 implements DIInterface{

    DI di;


    public void test(){
        di.sayHello();
    }

    @Autowired
    @Override
    public void inject(DI test1) {
        this.di = test1;
    }
}
