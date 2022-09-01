package com.helloworld.learn.app.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class IndexController {

    @GetMapping("/")
    public String getIndex(){
        return "Success";
    }
}
