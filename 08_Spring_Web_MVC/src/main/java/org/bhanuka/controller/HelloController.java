package org.bhanuka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@Controller       //** can return views, if the views are also in the project
@RequestMapping("/hello")
public class HelloController {
    public HelloController(){
        System.out.println("HelloController constructor");
    }

    @GetMapping
    public String hello(){
        return "index";
    }

    @GetMapping("two")
    public String hello2(){
        return "Hello World2";
    }

    @GetMapping("three")
    public String hello3(){
        return "Hello World3";
    }
}
