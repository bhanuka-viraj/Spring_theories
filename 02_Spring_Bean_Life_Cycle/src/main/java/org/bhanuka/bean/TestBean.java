package org.bhanuka.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class TestBean implements DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.out.println("TestBean Destroyed");
    }
}
