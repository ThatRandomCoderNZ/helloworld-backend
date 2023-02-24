package com.helloworld.learn.app.requests;

import org.springframework.web.bind.annotation.RequestBody;

public class ContentCreationRequest {

    private int number;

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return this.number;
    }
}
