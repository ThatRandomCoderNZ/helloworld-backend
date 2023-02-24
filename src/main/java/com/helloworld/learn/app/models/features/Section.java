package com.helloworld.learn.app.models.features;

import org.springframework.web.bind.annotation.Mapping;

import javax.persistence.*;
import java.util.List;

@Entity
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sectionId;

    @ManyToOne
    @JoinColumn(name ="language_id", nullable = false)
    private Language language;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL)
    private List<Lesson> lessons;

    @Column
    private String title;
    @Column
    private int difficulty;

    private SectionType type;

    public SectionType getType() {
        return type;
    }

    public void setType(SectionType type) {
        this.type = type;
    }




    public Section(){}

    public Section(Long id, String title, int difficulty, Long languageId)
    {
        super();
        this.sectionId = id;
        this.title = title;
        this.difficulty = difficulty;
        this.language = new Language(languageId, "", "");
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public void addLesson(Lesson lesson){
        this.lessons.add(lesson);
    }
}
