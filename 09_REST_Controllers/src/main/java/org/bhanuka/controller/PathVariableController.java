package org.bhanuka.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("path")
public class PathVariableController {

    //@GetMapping(path = "/{id}")
    @GetMapping("/{id}")
    public String getId( @PathVariable("id") String id){
        return "Id received : " + id;
    }

    // path/bhanuka/1234
    @GetMapping("/{name}/{id}")
    public String getNameAndId( @PathVariable("name") String name, @PathVariable("id") String id){
        return "Name received : " + name + " Id received : " + id;
    }



    // path/customer/I001
    @GetMapping(path = "customer/{id:[I][0-9]{3}}")
    public String getId2( @PathVariable("id") String id){
        return "Id received {id:[I][1-9]{3} : " + id;
    }


    //  path/item/I0001/branch/Nuwaraeliya
    @GetMapping(path = "item/{code:[I][0-9]{4}}/branch/{city}")
    public String getId3( @PathVariable("code") String code, @PathVariable("city") String city){
        return "Code received : " + code + " City received : " + city;
    }
}
