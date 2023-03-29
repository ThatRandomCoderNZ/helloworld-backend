package com.helloworld.learn.app.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class IndexController {

    @CrossOrigin
    @GetMapping("/")
    public String getIndex(){
        return "Success";
    }

    @CrossOrigin
    @GetMapping("/error")
    public String getError(){
        return "Something went wrong";
    }
}
