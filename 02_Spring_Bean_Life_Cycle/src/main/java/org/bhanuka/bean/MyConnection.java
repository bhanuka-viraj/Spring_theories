package org.bhanuka.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

public class MyConnection implements DisposableBean, BeanNameAware, BeanFactoryAware, InitializingBean {
    public MyConnection() {
        System.out.println("MyConnection constructor called");
    }


    @Override
    public void setBeanName(String name) {
        System.out.println("Bean Name(bean name awareness): "+name);
    }
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("Bean Factory(bean factory awareness): "+beanFactory);
    }
    @Override
    public void destroy() throws Exception {
        System.out.println("Myconnection destroyed");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Myconnection initialized");
    }
}
