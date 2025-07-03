package org.bhanuka.controller;

import org.bhanuka.dto.UserDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("form")
public class FormURLDataController {

//    @PostMapping(path = "save", params = {"id","name", "age"})
//    public String saveUser(@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("age") int age){
//        return "userID : " + id + "\n" + "userName : " + name + "\n" + "age : " + age;
//    }

//    @PostMapping(path = "save")
//    public String saveUser(@RequestParam Map<String, String> formdata){
//
//        return "userID : " + formdata.get("id") + "\n" + "userName : " + formdata.get("name") + "\n" + "age : " + formdata.get("age"); //userID : " + id + "\n" + "userName : " + name + "\n" + "age : " + age;
//    }

    @PostMapping(path = "save")
    public String saveUser( @ModelAttribute UserDto user){ // maps the params to the object
        //need the param keys and objects attribute names to be same

        return "userID : " + user.getId() + "\n" + "userName : " + user.getName() + "\n" + "age : " + user.getAge(); //userID : " + id + "\n" + "userName : " + name + "\n" + "age : " + age;
    }
}
