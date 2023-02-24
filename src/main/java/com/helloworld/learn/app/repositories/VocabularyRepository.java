package com.helloworld.learn.app.repositories;

import com.helloworld.learn.app.models.features.Vocabulary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VocabularyRepository extends CrudRepository<Vocabulary, Long> {
}
