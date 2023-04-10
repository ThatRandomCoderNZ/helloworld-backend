package com.helloworld.learn.app.requests;

import com.helloworld.learn.app.models.features.SectionType;

import java.util.List;

public class JsonImportSectionRequest {
    private String name;
    private List<JsonImportLessonRequest> lessons;
    private SectionType type;

    public JsonImportSectionRequest(){}

    public JsonImportSectionRequest(String name, List<JsonImportLessonRequest> lessons, SectionType type) {
        this.name = name;
        this.lessons = lessons;
        this.type = type;
    }

    public List<JsonImportLessonRequest> getLessons() {
        return lessons;
    }

    public void setLessons(List<JsonImportLessonRequest> lessons) {
        this.lessons = lessons;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SectionType getType() {
        return type;
    }

    public void setType(SectionType type) {
        this.type = type;
    }
}
