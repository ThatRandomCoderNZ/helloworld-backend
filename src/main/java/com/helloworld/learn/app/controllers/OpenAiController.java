package com.helloworld.learn.app.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.helloworld.learn.app.dtos.AiRequest;
import com.helloworld.learn.app.models.features.Language;
import com.helloworld.learn.app.models.openai.CompletionRequest;
import com.helloworld.learn.app.repositories.LanguageRepository;
import com.helloworld.learn.app.repositories.UserContextRepository;
import com.helloworld.learn.app.services.AiInteractionService;
import com.helloworld.learn.app.services.ApiService;
import com.helloworld.learn.app.services.GoogleApiInteractionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import java.io.IOException;

@RestController
@CrossOrigin
public class OpenAiController {

    private final UserContextRepository userContextRepository;

    private final AiInteractionService aiInteractionService;

    private final GoogleApiInteractionService googleApiInteractionService;

    private final LanguageRepository languageRepository;

    private final ApiService apiService;

    private final Logger logger;

    public OpenAiController(
            UserContextRepository userContextRepository,
            AiInteractionService aiInteractionService, GoogleApiInteractionService googleApiInteractionService, LanguageRepository languageRepository, ApiService apiService) {
        this.userContextRepository = userContextRepository;
        this.aiInteractionService = aiInteractionService;
        this.googleApiInteractionService = googleApiInteractionService;
        this.languageRepository = languageRepository;
        this.apiService = apiService;
        this.logger = LoggerFactory.getLogger(this.getClass());
    }


    @GetMapping("/sentence-with-word/{languageId}/{word}")
    public String generateSentenceWithWord(
            @PathVariable("languageId") Long languageId,
            @PathVariable("word") String word
    ) throws JsonProcessingException {
        String language = this.languageRepository.findById(languageId).orElseThrow().getName();
        String prompt = "Use " + word + " in a child friendly " + language + " sentence. Give the sentence in " + language +
                " first and then provide the English translation separated by a / character. In the format: Japanese/English";

        CompletionRequest sentence = this.apiService.makeRequest(prompt);
        return sentence.getChoices().get(0).getMessage().getContent();
    }


    @PostMapping("/test-speech/{languageId}")
    public byte[] testTextToSpeech(
            @PathVariable("languageId") Long languageId,
            @RequestBody AiRequest request
    ) throws IOException {
        return this.googleApiInteractionService.convertTextToSpeech(request.getPrompt(), languageId);
    }


    @DeleteMapping("/{userId}/aitest")
    @Transactional
    public void interactWithAi(
            @PathVariable("userId") Long userId
    ){
        this.userContextRepository.deleteByUserId(userId);
    }

    @PostMapping("/humour")
    public String humour(
            @RequestBody AiRequest request
    ) throws JsonProcessingException {
        String prompt = "Find a humorous response to: " + request.getPrompt();

        CompletionRequest moodCompletion = this.apiService.makeRequest(prompt);
        return moodCompletion.getChoices().get(0).getMessage().getContent();
    }


    @PostMapping("/mood")
    public String textMood(
            @RequestBody AiRequest request
    ) throws JsonProcessingException {
        String prompt = "Evaluate the humour in the following, with 0 being not funny and 10 being most funny: " + request.getPrompt();

        CompletionRequest moodCompletion = this.apiService.makeRequest(prompt);
        return moodCompletion.getChoices().get(0).getMessage().getContent();
    }

    @PostMapping("/audio/{languageId}")
    public ResponseEntity<String> processAudio(
            @PathVariable("languageId") Long languageId,
            @RequestParam("audio") MultipartFile audioFile
    ) throws IOException {
        String transcript = this.googleApiInteractionService.convertSpeechToText(audioFile, languageId);

        return ResponseEntity.ok(transcript);
    }

    @PostMapping("/app/{userId}/converse/{languageId}")
    public byte[] continueConversation(
            @RequestParam("audio") MultipartFile audioFile,
            @PathVariable("userId") Long userId,
            @PathVariable("languageId") Long languageId
    ) throws IOException {
        float currentTime = System.nanoTime();
        String transcript = this.googleApiInteractionService.convertSpeechToText(audioFile, languageId);
        double transcriptTime = System.nanoTime() - currentTime;
        logger.warn("Transcript: " + String.valueOf(transcriptTime / 1000000000));
        String aiResponse = this.aiInteractionService.getAiGeneratedResponse(transcript, userId, languageId);
        double aiTime = (System.nanoTime() - currentTime) - transcriptTime;
        logger.warn("Ai Response: " + String.valueOf(aiTime / 1000000000));
        byte[] speechResponse = this.googleApiInteractionService.convertTextToSpeech(aiResponse, languageId);
        logger.warn("Response to Speech: " + String.valueOf(((System.nanoTime() - currentTime) - aiTime ) / 1000000000));
        logger.warn("Total Time: " + String.valueOf((System.nanoTime() - currentTime) / 1000000000));

        return speechResponse;
    }

    @PostMapping("/grammar-notes")
    public String grammarNotes(
            @RequestBody AiRequest request
    ) throws JsonProcessingException {
        String prompt = "Highlight any flaws and provide suggestions for improvement in the following Spanish: "  + request.getPrompt();

        CompletionRequest grammarNotes = this.apiService.makeRequest(prompt);
        return grammarNotes.getChoices().get(0).getMessage().getContent();
    }

    @PostMapping("/{userId}/aitest/{languageId}")
    public String interactWithAi(
            @PathVariable("userId") Long userId,
            @PathVariable("languageId") Long languageId,
            @RequestBody AiRequest request
    ) throws JsonProcessingException {
        return this.aiInteractionService.getAiGeneratedResponse(request.getPrompt(), userId, languageId);
    }

    @GetMapping("ai/{languageId}/mood")
    public ResponseEntity<Integer> measureMood(
            @PathVariable("languageId") Long languageId,
            @RequestParam("mood") String mood,
            @RequestParam("sentence") String sentence
    ) throws JsonProcessingException {
        Language language = this.languageRepository.findById(languageId).orElseThrow();
        String languageName = language.getName();
        String prompt = "Measure out of 10 how " + mood + " the following " + languageName + " sentence is" +
                ": " + sentence + ". Give a numeric answer, out of 10.";
        String moodMeasurement = this.apiService.makeRequest(prompt).getChoices().get(0).getMessage().getContent();
        return ResponseEntity.ok(Integer.parseInt(moodMeasurement.strip()));
    }

    @GetMapping("ai/{languageId}/accuracy")
    public ResponseEntity<Integer> measureGrammaticalAccuracy(
            @PathVariable("languageId") Long languageId,
            @RequestParam("sentence") String sentence
    ) throws JsonProcessingException {
        Language language = this.languageRepository.findById(languageId).orElseThrow();
        String languageName = language.getName();
        String prompt = "Measure the grammatical accuracy of this " + languageName + " sentence :'" + sentence +
                "' out of 10. Give answer as a single number";
        String accuracyRating = this.apiService.makeRequest(prompt).getChoices().get(0).getMessage().getContent();
        return ResponseEntity.ok(Integer.parseInt(accuracyRating.strip()));
    }

    @GetMapping("ai/{languageId}/correctness")
    public ResponseEntity<Boolean> detectGrammaticalPart(
            @PathVariable("languageId") Long languageId,
            @RequestParam("prompt") String requestPrompt,
            @RequestParam("sentence") String sentence
    ) throws JsonProcessingException {
        Language language = this.languageRepository.findById(languageId).orElseThrow();
        String languageName = language.getName();
        String prompt = "Determine if the following " + languageName +
                " sentence does '" + requestPrompt +
                "' correctly: [" + sentence +"]. Give answer as a boolean {True/False}";

        logger.warn(prompt);
        String detection = this.apiService.makeRequest(prompt).getChoices().get(0).getMessage().getContent();
        logger.warn(detection);
        return ResponseEntity.ok(Boolean.parseBoolean(detection.strip().toLowerCase()));
    }
}
