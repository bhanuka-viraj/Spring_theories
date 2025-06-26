package org.bhanuka.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringBeanOne implements ApplicationContextAware, BeanFactoryAware, DisposableBean, BeanNameAware, InitializingBean {

    public SpringBeanOne(){
        System.out.println("spring bean one created");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("spring bean one factory aware");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("spring bean one name aware");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("spring bean one destroyed");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("spring bean one initialized");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("spring bean one context aware");
    }
}
