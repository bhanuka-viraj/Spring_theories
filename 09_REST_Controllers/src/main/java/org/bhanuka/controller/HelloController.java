package org.bhanuka.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("hello")
public class HelloController {

    @GetMapping
    public String sayHello() {
        return "Get mappping";
    }

    @PostMapping
    public String sayHelloPost() {
        return "Post mappping";
    }

    @DeleteMapping
    public String sayHelloDelete() {
        return "Delete mappping";
    }

    @PutMapping
    public String sayHelloPut() {
        return "Put mappping";
    }

    @PatchMapping
    public String sayHelloPatch() {
        return "Patch mappping";
    }
}
