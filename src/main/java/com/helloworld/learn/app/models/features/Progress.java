package com.helloworld.learn.app.models.features;

import com.helloworld.learn.app.models.user.DAOUser;

import jakarta.persistence.*;

@Entity
public class Progress
{
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private DAOUser user;

    @ManyToOne
    @JoinColumn(name = "vocabulary_id", nullable = false)
    private Vocabulary vocabulary;

    @Column
    private int progress;

    public Progress(){}

    public Progress(Long id, DAOUser user, Vocabulary vocabulary, int progress)
    {
        this.id = id;
        this.user = user;
        this.vocabulary = vocabulary;
        this.progress = progress;
    }

    public DAOUser getUser() {
        return user;
    }

    public void setUser(DAOUser user) {
        this.user = user;
    }

    public Vocabulary getVocabulary() {
        return vocabulary;
    }

    public void setVocabulary(Vocabulary vocabulary) {
        this.vocabulary = vocabulary;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
