package com.helloworld.learn.app.models.openai;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChoiceResponse {
    @JsonProperty
    private int index;

    @JsonProperty
    private MessageResponse message;

    @JsonProperty("finish_reason")
    private String finishReason;

    public ChoiceResponse(){}

    public ChoiceResponse(
            int index,
            MessageResponse message,
            String finishReason
    ){
        this.index = index;
        this.message = message;
        this.finishReason = finishReason;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getFinishReason() {
        return finishReason;
    }

    public void setFinishReason(String finishReason) {
        this.finishReason = finishReason;
    }

    public MessageResponse getMessage() {
        return message;
    }

    public void setMessage(MessageResponse message) {
        this.message = message;
    }
}
