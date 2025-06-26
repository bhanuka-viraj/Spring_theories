package org.bhanuka.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Boy {

    private Agreement girl;

    @Autowired
    public Boy(@Qualifier("girl2") Agreement girl){
        this.girl = girl;
        System.out.println("Boy created..");
    }

    public void chatWithGirl(){
        girl.chat();
    }
}
