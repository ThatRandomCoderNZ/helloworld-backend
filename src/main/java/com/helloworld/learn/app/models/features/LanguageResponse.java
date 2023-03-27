package com.helloworld.learn.app.models.features;

import java.util.List;

public class LanguageResponse {

    public String name;
    public Long id;
    public List<SectionResponse> sections;

    public LanguageResponse(Language language)
    {
        this.name = language.getName();
        this.id = language.getLanguageId();
        this.sections = language.getSections().stream().map(SectionResponse::new).toList();
    }
}
