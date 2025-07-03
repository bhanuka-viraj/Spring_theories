package org.bhanuka.controller;

import org.bhanuka.dto.AddressDto;
import org.bhanuka.dto.UserDto;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.*;

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

//    @PostMapping(path = "save")
//    public String saveUser( @ModelAttribute UserDto user){ // maps the params to the object
//        //need the param keys and objects attribute names to be same
//
//        return "userID : " + user.getId() + "\n" + "userName : " + user.getName() + "\n" + "age : " + user.getAge(); //userID : " + id + "\n" + "userName : " + name + "\n" + "age : " + age;
//    }

    @PostMapping(path = "save", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String saveUser( @RequestBody UserDto user){ // need jacksondatabind dependency to convert json to object
        return "userID : " + user.getId() + "\n" + "userName : " + user.getName() + "\n" + "age : " + user.getAge() + "\n" +
                "city : " + user.getAddress().getCity() + "\n" + "code : " + user.getAddress().getPostalCode() + "\n" +
                "province : " + user.getAddress().getProvince() ; //userID : " + id + "\n" + "userName : " + name + "\n" + "age : " + age;
    }

    @GetMapping
    public UserDto getUser() {
        UserDto user = new UserDto("I001", "Bhanuka", "20", new AddressDto("Colombo", 10000, "Western"));
        return user;
    }
}
