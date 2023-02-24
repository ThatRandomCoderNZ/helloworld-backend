package com.helloworld.learn.app.models.features;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@Entity
public class Vocabulary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long vocabularyId;


    @ManyToOne
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lesson;

    @OneToMany(mappedBy = "vocabulary", cascade = CascadeType.REMOVE)

    private List<Progress> progress;

    @Column
    private String nativeWord;


    @Column
    private String foreignWord;

    @Column
    @Nullable
    private String pronunciation;

    @Column
    @Nullable
    private String foreignAlternative;


    public Vocabulary(){}

    public Vocabulary(
            Long vocabularyId,
            Lesson lesson,
            String nativeWord,
            String foreignWord,
            @Nullable String pronunciation,
            @Nullable String foreignAlternative
    ) {
        super();
        this.vocabularyId = vocabularyId;
        this.lesson = lesson;
        this.nativeWord = nativeWord;
        this.foreignWord = foreignWord;
        this.pronunciation = pronunciation;
        this.foreignAlternative = foreignAlternative;
    }

    public Long getVocabularyId() {
        return vocabularyId;
    }

    public void setVocabularyId(Long vocabularyId) {
        this.vocabularyId = vocabularyId;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public String getNativeWord() {
        return nativeWord;
    }

    public void setNativeWord(String nativeWord) {
        this.nativeWord = nativeWord;
    }

    public String getForeignWord() {
        return foreignWord;
    }

    public void setForeignWord(String foreignWord) {
        this.foreignWord = foreignWord;
    }

    @Nullable
    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(@Nullable String pronunciation) {
        this.pronunciation = pronunciation;
    }

    @Nullable
    public String getForeignAlternative() {
        return foreignAlternative;
    }

    public void setForeignAlternative(@Nullable String foreignAlternative) {
        this.foreignAlternative = foreignAlternative;
    }


    public void addLesson(Lesson lesson)
    {
        this.lesson = lesson;
        lesson.addVocabulary(this);
    }
}
