package com.helloworld.learn.app.models.features;

public class VocabularyResponse {


    public Long id;
    public String foreignWord;
    public String nativeWord;
    public String pronunciation;
    public String foreignAlternative;

    public VocabularyResponse(){}

    public VocabularyResponse(Vocabulary vocabulary){
        this.id = vocabulary.getVocabularyId();
        this.foreignWord = vocabulary.getForeignWord();
        this.nativeWord = vocabulary.getNativeWord();
        this.pronunciation = vocabulary.getPronunciation();
        this.foreignAlternative = vocabulary.getForeignAlternative();
    }
}
