package com.helloworld.learn.app.models.features;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class GrammarLessonResponse {
    public Long id;
    public Long languageId;
    public String title;
    public int difficulty;
    public String prompt;

    public String explanation;

    public GrammarLessonResponse(){}
    public GrammarLessonResponse(GrammarLesson grammarLesson) {
        this.id = grammarLesson.getId();
        this.languageId = grammarLesson.getLanguage().getLanguageId();
        this.title = grammarLesson.getTitle();
        this.difficulty = grammarLesson.getDifficulty();
        this.prompt = grammarLesson.getPrompt();
        this.explanation = grammarLesson.getExplanation();
    }
}
