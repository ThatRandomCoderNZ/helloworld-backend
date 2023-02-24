package com.helloworld.learn.app.dtos;

public class AiRequest {

    private String prompt;

    public AiRequest(String prompt)
    {
        this.prompt = prompt;
    }

    public AiRequest(){}

    public String getPrompt() {
        return prompt;
    }



    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }




}
