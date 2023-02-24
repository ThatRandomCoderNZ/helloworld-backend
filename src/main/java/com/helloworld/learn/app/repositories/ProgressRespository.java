package com.helloworld.learn.app.repositories;

import com.helloworld.learn.app.models.features.Progress;
import com.helloworld.learn.app.models.features.Vocabulary;
import com.helloworld.learn.app.models.user.UserDTO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProgressRespository extends CrudRepository<Progress, Long> {

    Progress findByUserIdAndVocabulary(Long userId, Vocabulary vocabulary);

    List<Progress> findAllByUserIdAndVocabularyIn(Long userId, List<Vocabulary> vocabulary);
}
