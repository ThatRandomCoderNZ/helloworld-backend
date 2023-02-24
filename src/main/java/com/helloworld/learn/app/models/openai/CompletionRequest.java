package com.helloworld.learn.app.models.openai;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CompletionRequest {
    @JsonProperty
    private String id;
    @JsonProperty
    private String object;

    @JsonProperty
    private Long created;
    @JsonProperty
    private String model;

    @JsonProperty
    private List<ChoiceResponse> choices;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<ChoiceResponse> getChoices() {
        return choices;
    }

    public void setChoices(List<ChoiceResponse> choices) {
        this.choices = choices;
    }

    public UsageResponse getUsage() {
        return usage;
    }

    public void setUsage(UsageResponse usage) {
        this.usage = usage;
    }

    @JsonProperty
    private UsageResponse usage;

    public CompletionRequest(){}

    public CompletionRequest(
            String id,
            String object,
            Long created,
            String model,
            List<ChoiceResponse> choices,
            UsageResponse usage
    ){
        this.id = id;
        this.object = object;
        this.created = created;
        this.model = model;
        this.choices = choices;
        this.usage = usage;
    }

}
