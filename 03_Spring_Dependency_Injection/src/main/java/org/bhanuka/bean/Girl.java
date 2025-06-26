package org.bhanuka.bean;

import org.springframework.stereotype.Component;

@Component
public class Girl implements Agreement{
    public Girl(){
        System.out.println("Girl created..");
    }
    public void chat(){
        System.out.println("Girl is chatting...");
    }
}
