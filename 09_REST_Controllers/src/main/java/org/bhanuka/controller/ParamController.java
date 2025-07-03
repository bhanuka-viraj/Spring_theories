package org.bhanuka.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("param")
public class ParamController {

    @GetMapping(params = {"id"})
    public String getParam(@RequestParam("id") String id) {
        return "Param received : " + id;
    }

    // param/code?id=123&name=bhanuka
    @GetMapping(path = "/{code}", params = {"id","name"})
    public String getParam2(@PathVariable("code") String code, @RequestParam("id") String id, @RequestParam("name") String name) {
        return "Code : " + code + id + name;
    }
}
