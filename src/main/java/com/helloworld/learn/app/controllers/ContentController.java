package com.helloworld.learn.app.controllers;

import com.helloworld.learn.app.models.features.*;
import com.helloworld.learn.app.requests.*;
import com.helloworld.learn.app.services.ContentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173/")
public class ContentController {


    private final ContentService contentService;

    public ContentController(ContentService contentService){
        this.contentService = contentService;
    }

    @PostMapping ("/language")
    public void createLanguage(@RequestBody Language languageRequest){
        this.contentService.upsertLanguage(languageRequest);
    }

    @PutMapping ("/language")
    public void updateLanguage(@RequestBody Language languageRequest){
        this.contentService.upsertLanguage(languageRequest);
    }

    @DeleteMapping ("/language/{id}")
    public void deleteLanguage(@PathVariable("id") Long languageId){
        this.contentService.deleteLanguage(languageId);
    }

    @GetMapping("/language/{languageId}")
    public LanguageResponse getLanguage(@PathVariable("languageId") Long languageId)
    {
        Language language = this.contentService.getLanguage(languageId);
        return new LanguageResponse(language);
    }

    @GetMapping("/language")
    public List<LanguageResponse> getLanguages()
    {
        List<Language> languages = this.contentService.getAllLanguages();
        return languages.stream().map(LanguageResponse::new).toList();
    }

    @GetMapping("/language/plain")
    public List<PlainLanguageResponse> getPlainLanguages()
    {
        List<Language> languages = this.contentService.getAllLanguages();
        return languages.stream().map(PlainLanguageResponse::new).toList();
    }




    @PostMapping ("/language/{languageId}/section")
    public void createSection(@PathVariable("languageId") Long languageId, @RequestBody Section sectionRequest) throws Exception
    {
        sectionRequest.setLanguage(new Language(languageId, "", ""));
        this.contentService.upsertSection(sectionRequest);
    }

    @PostMapping("/language/{languageId}/section/{sectionId}/flipped")
    public void createFlippedSection(@PathVariable("languageId") Long languageId, @PathVariable("sectionId") Long sectionId){
        this.contentService.createFlipped(languageId, sectionId);
    }

    @PutMapping ("section")
    public void updateSection(@RequestBody Section sectionRequest) throws Exception
    {
        this.contentService.upsertSection(sectionRequest);
    }

    @DeleteMapping("/section/{sectionId}")
    public void deleteSection(@PathVariable("sectionId") Long sectionId)
    {
        this.contentService.deleteSection(sectionId);
    }

    @GetMapping("/language/{languageId}/section")
    public List<SectionResponse> getAllSectionsWithDifficultyAndType(
            @PathVariable("languageId") Long languageId,
            @RequestParam("difficulty") int difficulty,
            @RequestParam("type") SectionType type
    )
    {
        List<Section> sections = this.contentService.getAllSectionsOfDifficultyAndType(languageId, difficulty, type);
        return sections.stream().map(SectionResponse::new).toList();
    }

    @GetMapping("/section/{sectionId}")
    public SectionResponse getSection(@PathVariable("sectionId") Long sectionId)
    {
        Section section = this.contentService.getSection(sectionId);
        return new SectionResponse(section);
    }


    @PostMapping ("language/{languageId}/section/{sectionId}/lesson")
    public void createLesson(
            @PathVariable("languageId") Long languageId,
            @PathVariable("sectionId") Long sectionId,
            @RequestBody Lesson lessonRequest
    ) {
        lessonRequest.setSection(new Section(sectionId, "", 0, languageId, SectionType.FOREIGN));
        this.contentService.upsertLesson(lessonRequest);
    }

    @PutMapping ("lesson")
    public void updateLesson(
            @RequestBody Lesson lessonRequest
    ) {
        this.contentService.upsertLesson(lessonRequest);
    }

    @DeleteMapping("/lesson/{lessonId}")
    public void deleteLesson(@PathVariable("lessonId") Long lessonId)
    {
        this.contentService.deleteLesson(lessonId);
    }

    @GetMapping("/lesson/{lessonId}")
    public LessonResponse getLesson(@PathVariable("lessonId") Long lessonId)
    {
        Lesson lesson = this.contentService.getLesson(lessonId);
        return new LessonResponse(lesson);
    }

    @GetMapping("/section/{sectionId}/lesson")
    public List<LessonResponse> getAllLessons(
            @PathVariable("sectionId") Long sectionId
    ) {
        List<Lesson> sections = this.contentService.getAllLessons(sectionId);
        return sections.stream().map(LessonResponse::new).toList();
    }


    @PostMapping("section/{sectionId}/lesson/{lessonId}/vocab")
    public void createVocabulary(
            @PathVariable("sectionId") Long sectionId,
            @PathVariable("lessonId") Long lessonId,
            @RequestBody Vocabulary vocabularyRequest
    ) {
        vocabularyRequest.setLesson(new Lesson(lessonId, "", "", sectionId));
        this.contentService.upsertVocabulary(vocabularyRequest);
    }

    @PutMapping("vocab")
    public void updateVocabulary(
            @RequestBody Vocabulary vocabularyUpdateRequest
    ) {
        this.contentService.upsertVocabulary(vocabularyUpdateRequest);
    }

    @DeleteMapping("vocab/{id}")
    public void deleteVocabulary(@PathVariable("id") Long vocabId) {
        this.contentService.deleteVocabulary(vocabId);
    }




    @PostMapping ("{languageId}/grammar")
    public void createGrammarLesson(
            @PathVariable("languageId") Long languageId,
            @RequestBody GrammarLesson lessonRequest
    ) {
        lessonRequest.setLanguage(new Language(languageId, "", ""));
        this.contentService.upsertGrammarLesson(lessonRequest);
    }

    @PutMapping ("grammar")
    public void updateGrammarLesson(
            @RequestBody GrammarLesson lessonRequest
    ) {
        this.contentService.upsertGrammarLesson(lessonRequest);
    }

    @DeleteMapping("/grammar/{lessonId}")
    public void deleteGrammarLesson(@PathVariable("lessonId") Long lessonId)
    {
        this.contentService.deleteGrammarLesson(lessonId);
    }

    @GetMapping("/grammar/{lessonId}")
    public GrammarLessonResponse getGrammarLesson(@PathVariable("lessonId") Long lessonId)
    {
        GrammarLesson lesson = this.contentService.getGrammarLesson(lessonId);
        return new GrammarLessonResponse(lesson);
    }

    @GetMapping("{languageId}/grammar")
    public List<GrammarLessonResponse> getAllGrammarLessons(
            @PathVariable("languageId") Long languageId
    ) {
        List<GrammarLesson> lessons = this.contentService.getAllGrammarLessonsByLanguage(languageId);
        return lessons.stream().map(GrammarLessonResponse::new).toList();
    }




    @PostMapping("/{languageId}/import-json/{type}")
    public void importDataFromJson(
            @PathVariable("languageId") Long languageId,
            @PathVariable("type") SectionType type,
            @RequestBody List<JsonImportSectionRequest> importData
    ){
        Language language = new Language(languageId, "", "");
        importData.forEach(importSection -> {
            Section section = new Section();
            section.setTitle(importSection.getName());
            section.setDifficulty(0);
            section.setType(type);
            section.setLanguage(language);
            contentService.upsertSection(section);

            for (JsonImportLessonRequest importLesson : importSection.getLessons()) {
                Lesson lesson = new Lesson();
                lesson.setName(importLesson.getName());
                lesson.setType("Vocabulary");
                lesson.setSection(section);
                contentService.upsertLesson(lesson);

                importLesson.getVocab().forEach(importVocab -> {
                    Vocabulary vocab = new Vocabulary();
                    vocab.setForeignWord(importVocab.getForeignWord());
                    vocab.setNativeWord(importVocab.getNativeWord());
                    vocab.setForeignAlternative(importVocab.getAlternativeWord());
                    vocab.setLesson(lesson);
                    contentService.upsertVocabulary(vocab);
                });

            }
        });
    }


}
