package com.insat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class IndexControler {


    @GetMapping("/hello")
    public String sayHello(){
        return "Hi!";
    }

    @GetMapping("/bye")
    public String sayBay(){
        return "Bye!";
    }
}
