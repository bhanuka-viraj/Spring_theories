package org.bhanuka.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class SpringBean1 {


    // @Value annotation sets the value of parameters/variables in the runtime when the object is created
    @Autowired(required = false)
    public SpringBean1 (@Value("Bhanuka")String name,
                        @Value("10") int number,
                        @Value("true") boolean flag) {
        System.out.println("Spring bean 1 flag value is: " + flag);
        System.out.println("Spring bean 1 number value is: " + number);
        System.out.println("Spring bean 1 name value is: " + name);
    }


    @Autowired(required = false) // when the required= false set to both constructors, it will choose the constructor with most number of parameters
    public SpringBean1 (@Value("Bhanuka")String name,
                        @Value("10") int number) {
        System.out.println("Spring bean 1 number value is: " + number);
        System.out.println("Spring bean 1 name value is: " + name);
    }

    /*
    * when there are two or more constructors, we should set the autowire annotation to a/one constructor
      or otherwise it won't be able to find the matching constructor for autowiring.
    * if the autowire annotation set to all constructors, we should use the @Autowired(required = false)/ required attribute
    * when the required= false set to both constructors, it will choose the constructor with most number of parameters
    * */
}
