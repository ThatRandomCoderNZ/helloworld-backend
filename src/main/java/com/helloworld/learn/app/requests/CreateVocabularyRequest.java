package com.helloworld.learn.app.requests;

import com.helloworld.learn.app.models.features.Lesson;
import org.springframework.lang.Nullable;

import javax.persistence.Column;

public class CreateVocabularyRequest {

    private Lesson lessonId;

    private String nativeWord;

    private String foreignWord;

    private String pronunciation;

    private String foreignAlternative;
}
