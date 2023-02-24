package com.helloworld.learn.app.repositories;

import com.helloworld.learn.app.models.features.GrammarLesson;
import com.helloworld.learn.app.models.features.Language;
import com.helloworld.learn.app.models.features.Lesson;
import com.helloworld.learn.app.models.features.Section;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrammarLessonRepository  extends CrudRepository<GrammarLesson, Long> {

    Iterable<GrammarLesson> findAllByLanguageAndDifficulty(Language language, int difficulty);

    Iterable<GrammarLesson> findAllByLanguage(Language language);
}
