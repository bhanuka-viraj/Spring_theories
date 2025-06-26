package org.bhanuka.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringBeanTwo implements ApplicationContextAware, BeanFactoryAware, DisposableBean, BeanNameAware, InitializingBean {

    public SpringBeanTwo(){
        System.out.println("spring bean two created");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("spring bean two factory aware");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("spring bean two name aware");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("spring bean two destroyed");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("spring bean two initialized");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("spring bean two context aware");
    }
}
