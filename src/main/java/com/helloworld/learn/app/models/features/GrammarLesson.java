package com.helloworld.learn.app.models.features;

import jakarta.persistence.*;

@Entity
public class GrammarLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;


    @ManyToOne
    @JoinColumn(name ="language_id", nullable = false)
    private Language language;


    @Column
    private String title;

    @Column
    private int difficulty;

    @Column
    private String prompt;

    @Column
    private String explanation;

    public GrammarLesson(){}
    public GrammarLesson(Long id, Language language, String title, int difficulty, String prompt, String explanation) {
        this.id = id;
        this.language = language;
        this.title = title;
        this.difficulty = difficulty;
        this.prompt = prompt;
        this.explanation = explanation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
