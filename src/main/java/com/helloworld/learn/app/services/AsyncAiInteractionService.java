package com.helloworld.learn.app.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.helloworld.learn.app.models.openai.CompletionRequest;
import com.helloworld.learn.app.models.openai.UserContext;
import com.helloworld.learn.app.repositories.UserContextRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncAiInteractionService {

    private final Logger logger;

    private final UserContextRepository userContextRepository;

    private final ApiService apiService;


    public AsyncAiInteractionService(UserContextRepository userContextRepository, ApiService apiService){
        this.userContextRepository = userContextRepository;
        this.apiService = apiService;
        this.logger = LoggerFactory.getLogger(this.getClass());
    }



    @Async
    public void generateSummary(UserContext userContext, String requestPrompt, String promptResponse) throws JsonProcessingException {
        String summaryPrompt = "Summarise the entire interaction of: User: '" +
                requestPrompt + "' , Diego: '" + promptResponse + "'";
        this.logger.warn("SUMMARY PROMPT: " + summaryPrompt);
        CompletionRequest summaryCompletion = this.apiService.makeRequest(summaryPrompt);
        String newContext = summaryCompletion.getChoices().get(0).getMessage().getContent().strip().replaceAll("\"", "'");
        this.logger.warn("SUMMARY RESPONSE: " + newContext);
        userContext.setContext(newContext);
        this.userContextRepository.save(userContext);
    }
}
