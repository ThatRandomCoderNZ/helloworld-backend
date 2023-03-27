package com.helloworld.learn.app.models.openai;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageResponse {
    @JsonProperty
    private String role;
    @JsonProperty
    private String content;

    public MessageResponse(){}

    public MessageResponse(String role, String content) {
        this.role = role;
        this.content = content;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
