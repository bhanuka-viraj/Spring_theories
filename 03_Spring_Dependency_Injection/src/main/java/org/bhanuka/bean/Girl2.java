package org.bhanuka.bean;

import org.springframework.stereotype.Component;

@Component("girl2")
public class Girl2 implements Agreement{
    public Girl2(){
        System.out.println("Girl2 created...");
    }
    @Override
    public void chat() {
        System.out.println("Girl2 is chatting...");
    }
}
