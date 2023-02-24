package com.helloworld.learn.app.requests;

import com.helloworld.learn.app.models.features.Section;
import com.helloworld.learn.app.models.features.Vocabulary;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;

public class CreateLessonRequest {
    private Long sectionId;

    private String name;

    private String type;

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

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }
}
