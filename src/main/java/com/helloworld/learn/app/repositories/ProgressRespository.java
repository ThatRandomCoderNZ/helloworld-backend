package com.helloworld.learn.app.repositories;

import com.helloworld.learn.app.models.features.Progress;
import com.helloworld.learn.app.models.features.Vocabulary;
import com.helloworld.learn.app.models.user.UserDTO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProgressRespository extends CrudRepository<Progress, Long> {

    Progress findByUserUuidAndVocabulary(String userUuid, Vocabulary vocabulary);

    List<Progress> findAllByUserUuidAndVocabularyIn(String userUuid, List<Vocabulary> vocabulary);
}
