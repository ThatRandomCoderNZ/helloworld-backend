package com.helloworld.learn.app.repositories;

import com.helloworld.learn.app.models.features.Lesson;
import com.helloworld.learn.app.models.features.Section;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends CrudRepository<Lesson, Long> {

    Iterable<Lesson> findAllBySection(Section section);
}
