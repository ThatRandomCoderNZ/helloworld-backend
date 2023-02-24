package com.helloworld.learn.app.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.helloworld.learn.app.dtos.AiRequest;
import com.helloworld.learn.app.models.openai.CompletionRequest;
import com.helloworld.learn.app.repositories.UserContextRepository;
import com.helloworld.learn.app.services.AiInteractionService;
import com.helloworld.learn.app.services.ApiService;
import com.helloworld.learn.app.services.GoogleApiInteractionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@RestController
@CrossOrigin
public class OpenAiController {

    private final String apiKey = "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";

    private final UserContextRepository userContextRepository;

    private final AiInteractionService aiInteractionService;

    private final GoogleApiInteractionService googleApiInteractionService;

    private final ApiService apiService;

    private final Logger logger;

    public OpenAiController(
            UserContextRepository userContextRepository,
            AiInteractionService aiInteractionService, GoogleApiInteractionService googleApiInteractionService, ApiService apiService) {
        this.userContextRepository = userContextRepository;
        this.aiInteractionService = aiInteractionService;
        this.googleApiInteractionService = googleApiInteractionService;
        this.apiService = apiService;
        this.logger = LoggerFactory.getLogger(this.getClass());
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
        return moodCompletion.getChoices().get(0).getText();
    }


    @PostMapping("/mood")
    public String textMood(
            @RequestBody AiRequest request
    ) throws JsonProcessingException {
        String prompt = "Evaluate the humour in the following, with 0 being not funny and 10 being most funny: " + request.getPrompt();

        CompletionRequest moodCompletion = this.apiService.makeRequest(prompt);
        return moodCompletion.getChoices().get(0).getText();
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
        String prompt = "Highlight any flaws and provide suggestions for improvement in the following English: "  + request.getPrompt();

        CompletionRequest grammarNotes = this.apiService.makeRequest(prompt);
        return grammarNotes.getChoices().get(0).getText();
    }

    @PostMapping("/{userId}/aitest/{languageId}")
    public String interactWithAi(
            @PathVariable("userId") Long userId,
            @PathVariable("languageId") Long languageId,
            @RequestBody AiRequest request
    ) throws JsonProcessingException {
        return this.aiInteractionService.getAiGeneratedResponse(request.getPrompt(), userId, languageId);
    }
}
