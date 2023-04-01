package com.helloworld.learn.app.services;

import com.helloworld.learn.app.models.features.Progress;
import com.helloworld.learn.app.models.features.Vocabulary;
import com.helloworld.learn.app.repositories.ProgressRespository;
import com.helloworld.learn.app.repositories.VocabularyRepository;
import org.apache.commons.logging.LogFactory;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ProgressService {


    private final ProgressRespository progressRespository;
    private final VocabularyRepository vocabularyRepository;

    public ProgressService(ProgressRespository progressRespository, VocabularyRepository vocabularyRepository){
        this.progressRespository = progressRespository;
        this.vocabularyRepository = vocabularyRepository;
    }

    public Progress getProgressByIds(String userUuid, Long vocabularyId){
        Vocabulary vocabulary = this.vocabularyRepository.findById(vocabularyId).orElseThrow();
        return this.progressRespository.findByUserUuidAndVocabulary(userUuid, vocabulary);
    }

    public List<Progress> getAllProgressByIds(String userUuid, List<Long> vocabularyIds){
        List<Vocabulary> vocabulary = new ArrayList<>();
        this.vocabularyRepository.findAllById(vocabularyIds).forEach(vocabulary::add);

        return this.progressRespository.findAllByUserUuidAndVocabularyIn(userUuid, vocabulary);
    }

    public void upsertProgress(Progress progress)
    {
        this.progressRespository.save(progress);
    }
}
