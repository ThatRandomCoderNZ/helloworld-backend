package com.helloworld.learn.app.models.features;

import javax.persistence.*;
import java.util.List;

@Entity
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long languageId;

    @Column
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column
    private String code;

    @OneToMany(mappedBy = "language", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Section> sections;

    public Language(){}

    public Language(Long id, String name, String code)
    {
        this.languageId = id;
        this.name = name;
        this.code = code;
    }

    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public void addSection(Section section)
    {
        this.sections.add(section);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
