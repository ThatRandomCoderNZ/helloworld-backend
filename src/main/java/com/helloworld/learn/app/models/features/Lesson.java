package com.helloworld.learn.app.models.features;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long lessonId;

    @ManyToOne
    @JoinColumn(name = "section_id", nullable = false)
    private Section section;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    private List<Vocabulary> vocabulary;

    @Column
    private String name;

    @Column
    private String type; //Will most likely be enum

    public Lesson(){}

    public Lesson(Long id, String name, String type, Long sectionId){
        this.lessonId = id;
        this.name = name;
        this.type = type;
        this.section = new Section(sectionId, "", 0, 0L);
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public void addVocabulary(Vocabulary vocabulary)
    {
        this.vocabulary.add(vocabulary);
    }

    public List<Vocabulary> getVocabulary() {
        return vocabulary;
    }

    public void setVocabulary(List<Vocabulary> vocabulary) {
        this.vocabulary = vocabulary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
