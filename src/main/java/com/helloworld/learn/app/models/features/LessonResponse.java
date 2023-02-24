package com.helloworld.learn.app.models.features;

import java.util.List;

public class LessonResponse {


    public Long id;
    public String name;
    public String type;
    public List<VocabularyResponse> vocabulary;

    public LessonResponse(Lesson lesson)
    {
        this.id = lesson.getLessonId();
        this.name = lesson.getName();
        this.type = lesson.getType();
        this.vocabulary = lesson.getVocabulary().stream().map(VocabularyResponse::new).toList();
    }
}
