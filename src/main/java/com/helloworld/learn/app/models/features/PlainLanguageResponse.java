package com.helloworld.learn.app.models.features;

import java.util.List;

public class PlainLanguageResponse {

    public String name;
    public Long id;

    public PlainLanguageResponse(Language language)
    {
        this.name = language.getName();
        this.id = language.getLanguageId();
    }
}
