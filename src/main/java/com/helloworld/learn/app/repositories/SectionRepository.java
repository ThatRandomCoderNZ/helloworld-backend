package com.helloworld.learn.app.repositories;

import com.helloworld.learn.app.models.features.Language;
import com.helloworld.learn.app.models.features.Section;
import com.helloworld.learn.app.models.features.SectionType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends CrudRepository<Section, Long> {
    Iterable<Section> findAllByLanguageAndDifficultyAndType(Language language, int difficulty, SectionType type);
}
