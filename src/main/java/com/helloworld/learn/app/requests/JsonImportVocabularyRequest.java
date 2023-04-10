package com.helloworld.learn.app.requests;

public class JsonImportVocabularyRequest {
    private String foreignWord;
    private String nativeWord;

    private String alternativeWord;

    public JsonImportVocabularyRequest(){}

    public JsonImportVocabularyRequest(String foreignWord, String nativeWord, String alternativeWord) {
        this.foreignWord = foreignWord;
        this.nativeWord = nativeWord;
        this.alternativeWord = alternativeWord;
    }

    public String getForeignWord() {
        return foreignWord;
    }

    public void setForeignWord(String foreignWord) {
        this.foreignWord = foreignWord;
    }

    public String getNativeWord() {
        return nativeWord;
    }

    public void setNativeWord(String nativeWord) {
        this.nativeWord = nativeWord;
    }

    public String getAlternativeWord() {
        return alternativeWord;
    }

    public void setAlternativeWord(String alternativeWord) {
        this.alternativeWord = alternativeWord;
    }
}
