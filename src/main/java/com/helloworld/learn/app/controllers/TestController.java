package com.helloworld.learn.app.controllers;

import com.helloworld.learn.app.models.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.helloworld.learn.app.repositories.TestRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class TestController {

    private final TestRepository testRepository;

    public TestController(TestRepository testRepository){
        this.testRepository = testRepository;
    }

    @GetMapping("/numbers")
    public List<Test> getIntegers(){
        List<Test> tests = new ArrayList<>();
        testRepository.findAll().forEach(tests::add);
        return tests;
    }

    @PostMapping("/add")
    public String addNewNumber(){
        Test test = new Test();
        test.setName("Swag");
        testRepository.save(test);
        return "Success";
    }

}
