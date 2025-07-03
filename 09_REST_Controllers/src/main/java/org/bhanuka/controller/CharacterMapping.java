package org.bhanuka.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("char")
public class CharacterMapping {

    // char/item/I123  - works
    // char/item/123   - doesn't work
    // char/item/i123  - doesn't work

    @GetMapping("item/I???")
    public String getItem() {
        return "Item";
    }


    // char/item/123I
    @GetMapping("item/???I")
    public String getItem2() {
        return "Item2";
    }


    // char/1234/search       4 values needed before the search
    @GetMapping("????/search")
    public String search() {
        return "Search";
    }
}
