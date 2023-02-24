package com.helloworld.learn.app.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.helloworld.learn.app.models.features.Language;
import com.helloworld.learn.app.models.openai.CompletionRequest;
import com.helloworld.learn.app.models.openai.UserContext;
import com.helloworld.learn.app.models.user.DAOUser;
import com.helloworld.learn.app.repositories.LanguageRepository;
import com.helloworld.learn.app.repositories.UserContextRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AiInteractionService {


    private final Logger logger;

    private final UserContextRepository userContextRepository;

    private final LanguageRepository languageRepository;

    private final AsyncAiInteractionService asyncAiInteractionService;

    private final ApiService apiService;

    public AiInteractionService(
            UserContextRepository userContextRepository,
            LanguageRepository languageRepository,
            AsyncAiInteractionService asyncAiInteractionService, ApiService apiService) {
        this.languageRepository = languageRepository;
        this.asyncAiInteractionService = asyncAiInteractionService;
        this.apiService = apiService;
        this.logger = LoggerFactory.getLogger(this.getClass());
        this.userContextRepository = userContextRepository;
    }


    public String getAiGeneratedResponse(String requestPrompt, Long userId, Long languageId) throws JsonProcessingException {
        UserContext userContext = this.userContextRepository.findByUserId(userId);

        if(userContext == null){
            userContext = new UserContext();
            userContext.setUser(new DAOUser(userId, "", ""));
        }

        String currentContext = userContext.getContext() != null ? userContext.getContext() : "";

        Language language = languageRepository.findById(languageId).orElseThrow();

        String prompt = "From the perspective of Diego an Engineering student who enjoys reading and playing piano, " +
                "speaking with User, with current conversational context of '" + currentContext + "'. ";

        prompt += "Give a natural response in "  + language.getName() + " to '" + requestPrompt + "' that continues the conversation.";

        LoggerFactory.getLogger(this.getClass()).warn("Original prompt: " + prompt);

        CompletionRequest completion = this.apiService.makeRequest(prompt);

        LoggerFactory.getLogger(this.getClass()).warn("Tokens: " + completion.getUsage().getTotalTokens());

        String promptResponse = completion.getChoices().get(0).getText().strip();

        this.asyncAiInteractionService.generateSummary(userContext, requestPrompt, promptResponse);

        return promptResponse;
    }



}
