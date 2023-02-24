package com.helloworld.learn.app.models.openai;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChoiceResponse {
    @JsonProperty
    private String text;
    @JsonProperty
    private int index;
    @JsonProperty
    private boolean logprobs;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isLogprobs() {
        return logprobs;
    }

    public void setLogprobs(boolean logprobs) {
        this.logprobs = logprobs;
    }

    public String getFinishReason() {
        return finishReason;
    }

    public void setFinishReason(String finishReason) {
        this.finishReason = finishReason;
    }

    @JsonProperty("finish_reason")
    private String finishReason;

    public ChoiceResponse(){}

    public ChoiceResponse(
        String text,
        int index,
        boolean logprobs,
        String finishReason
    ){
        this.text = text;
        this.index = index;
        this.logprobs = logprobs;
        this.finishReason = finishReason;
    }
}
