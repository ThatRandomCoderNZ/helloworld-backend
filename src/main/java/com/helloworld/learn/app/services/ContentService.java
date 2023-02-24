package com.helloworld.learn.app.services;

import com.helloworld.learn.app.models.features.*;
import com.helloworld.learn.app.repositories.LanguageRepository;
import com.helloworld.learn.app.repositories.LessonRepository;
import com.helloworld.learn.app.repositories.SectionRepository;
import com.helloworld.learn.app.repositories.VocabularyRepository;
import com.helloworld.learn.app.requests.CreateLanguageRequest;
import com.helloworld.learn.app.requests.CreateLessonRequest;
import com.helloworld.learn.app.requests.CreateSectionRequest;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContentService {

    private final VocabularyRepository vocabularyRepository;

    private final LanguageRepository languageRepository;

    private final SectionRepository sectionRepository;

    private final LessonRepository lessonRepository;

    public ContentService(
            VocabularyRepository vocabularyRepository,
            LanguageRepository languageRepository,
            SectionRepository sectionRepository,
            LessonRepository lessonRepository
    ) {
        this.vocabularyRepository = vocabularyRepository;
        this.languageRepository = languageRepository;
        this.sectionRepository = sectionRepository;
        this.lessonRepository = lessonRepository;
    }


    public Language getLanguage(Long id){
        return this.languageRepository.findById(id).orElseThrow();
    }

    public List<Language> getAllLanguages()
    {
        List<Language> languages = new ArrayList<>();
        this.languageRepository.findAll().forEach(languages::add);
        return languages;
    }

    public void upsertLanguage(Language language)
    {
        this.languageRepository.save(language);
    }

    public void deleteLanguage(Long languageId)
    {
        this.languageRepository.deleteById(languageId);
    }

    public Section getSection(Long sectionId){
        return this.sectionRepository.findById(sectionId).orElseThrow();
    }

    public List<Section> getAllSectionsOfDifficultyAndType(Long languageId, int difficulty, SectionType type){
        List<Section> sections = new ArrayList<>();
        Language language = this.languageRepository.findById(languageId).orElseThrow();
        this.sectionRepository.findAllByLanguageAndDifficultyAndType(language, difficulty, type).forEach(sections::add);
        return sections;
    }

    public void upsertSection(Section section){
        if(section.getSectionId() != null){
            Section oldSection = sectionRepository.findById(section.getSectionId()).orElseThrow();
            section.setLanguage(oldSection.getLanguage());
        }
        this.sectionRepository.save(section);
    }

    public void createFlipped(Long languageId, Long sectionId){
        Section originalSection = this.sectionRepository.findById(sectionId).orElseThrow();
        Section flippedSection = new Section();
        flippedSection.setLanguage(new Language(languageId, "", ""));
        flippedSection.setDifficulty(originalSection.getDifficulty());
        flippedSection.setTitle(originalSection.getTitle());
        flippedSection.setType(originalSection.getType() == SectionType.NATIVE ? SectionType.FOREIGN : SectionType.NATIVE);
        this.sectionRepository.save(flippedSection);

        originalSection.getLessons().forEach(lesson -> {
            Lesson flippedLesson = new Lesson();
            flippedLesson.setName(lesson.getName());
            flippedLesson.setSection(flippedSection);
            this.lessonRepository.save(flippedLesson);

            lesson.getVocabulary().forEach(vocabulary -> {
                Vocabulary flipped = new Vocabulary();
                flipped.setNativeWord(vocabulary.getForeignWord());
                flipped.setForeignWord(vocabulary.getNativeWord());
                flipped.setLesson(flippedLesson);
                this.vocabularyRepository.save(flipped);
            });
        });
    }

    public void deleteSection(Long sectionId)
    {
        this.sectionRepository.deleteById(sectionId);
    }

    public void upsertVocabulary(Vocabulary vocabulary)
    {
        if(vocabulary.getVocabularyId() != null){
            Vocabulary oldVocab = vocabularyRepository.findById(vocabulary.getVocabularyId()).orElseThrow();
            vocabulary.setLesson(oldVocab.getLesson());
        }

        this.vocabularyRepository.save(vocabulary);
    }

    public void deleteVocabulary(Long vocabId)
    {
        this.vocabularyRepository.deleteById(vocabId);
    }

    public Vocabulary getVocabulary(Long vocabId)
    {
        return this.vocabularyRepository.findById(vocabId).orElseThrow();
    }


    public void upsertLesson(Lesson lessonRequest) {
        if(lessonRequest.getLessonId() != null){
            Lesson oldLesson = lessonRepository.findById(lessonRequest.getLessonId()).orElseThrow();
            lessonRequest.setSection(oldLesson.getSection());
        }
        this.lessonRepository.save(lessonRequest);
    }

    public void deleteLesson(Long lessonId)
    {
        this.lessonRepository.deleteById(lessonId);
    }


    public Lesson getLesson(Long lessonId)
    {
        return this.lessonRepository.findById(lessonId).orElseThrow();
    }

    public List<Lesson> getAllLessons(Long sectionId)
    {
        List<Lesson> lessons = new ArrayList<>();
        Section section = this.sectionRepository.findById(sectionId).orElseThrow();
        this.lessonRepository.findAllBySection(section).forEach(lessons::add);
        return lessons;
    }


}
