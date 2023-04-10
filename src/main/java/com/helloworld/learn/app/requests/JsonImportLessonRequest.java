package com.helloworld.learn.app.requests;

import java.util.List;

public class JsonImportLessonRequest {
    private String name;
    private List<JsonImportVocabularyRequest> vocab;

    public JsonImportLessonRequest(){}

    public JsonImportLessonRequest(String name, List<JsonImportVocabularyRequest> data) {
        this.name = name;
        this.vocab = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<JsonImportVocabularyRequest> getVocab() {
        return vocab;
    }

    public void setVocab(List<JsonImportVocabularyRequest> vocab) {
        this.vocab = vocab;
    }
}
