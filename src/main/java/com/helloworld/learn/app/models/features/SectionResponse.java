package com.helloworld.learn.app.models.features;

import java.util.List;

public class SectionResponse {

    public String title;
    public Long sectionId;

    public int difficulty;

    public SectionType type;

    public List<LessonResponse> lessons;

    public SectionResponse(){}

    public SectionResponse(Section section)
    {
        this.title = section.getTitle();
        this.difficulty = section.getDifficulty();
        this.sectionId = section.getSectionId();
        this.lessons = section.getLessons().stream().map(LessonResponse::new).toList();
        this.type = section.getType();
    }
}
