package org.bhanuka.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wild")
public class WildCardMappingController {

    // ------- gets all parts ----------
    // wild/item/13546     - works
    // wild/item/13546/    - works
    // wild/item/13546/hello - works
    // wild/item/           - works
    @GetMapping("/item/**")
    public String getItems(){
        return "Items";
    }


    // --- exclude the slashes so can get only one part -----
    // wild/item/123     - works
    // wild/item/123/    - not works
    // wild/item/123/hello - not works
    @GetMapping("/item/*")
    public String getItems1(){
        return "Items1";
    }

    // wild/item/123/hello      - works
    @GetMapping("/item/*/hello")
    public String getItems2(){
        return "Items2";
    }

}
