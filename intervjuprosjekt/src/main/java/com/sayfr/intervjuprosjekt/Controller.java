package com.sayfr.intervjuprosjekt;

import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class Controller {
    @GetMapping(value = "/")
    //@RequestMapping("/")
    public String index() {
        return "Test";
    }
}