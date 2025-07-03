package org.bhanuka.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ExactMappingController {

    @GetMapping("/hello/one")
    public String sayHelloOne() {
        return "Get mappping one";
    }

    @GetMapping("/hello/two")
    public String sayHelloTwo() {
        return "Get mappping two";
    }

    @GetMapping("/hello/three")
    public String sayHelloThree() {
        return "Get mappping three";
    }
}
