package com.helloworld.learn.app.models.features;

public class ProgressResponse {
    public Long id;
    public int progress;
    public Long userId;

    public Long vocabularyId;

    public ProgressResponse(){}

    public ProgressResponse(Progress progress){
        this.id = progress.getId();
        this.progress = progress.getProgress();
        this.userId = progress.getUser().getId();
        this.vocabularyId = progress.getVocabulary().getVocabularyId();
    }
}
