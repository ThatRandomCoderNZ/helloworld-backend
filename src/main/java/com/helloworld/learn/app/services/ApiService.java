package com.helloworld.learn.app.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helloworld.learn.app.models.openai.CompletionRequest;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {

    private final String apiKey = "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";

    public CompletionRequest makeRequest(String prompt) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String resource = "https://api.openai.com/v1/completions";
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        //String prompt = "Does the following spanish sentence have a noun and verb and correct grammar: 'Hago pizza'. Give true/false.";
        String requestJson = "{\"model\": \"text-davinci-003\", \"prompt\": \"" + prompt + "\", \"temperature\": 0, \"max_tokens\": 1000}";

        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        ResponseEntity<String> response = restTemplate.exchange(resource, HttpMethod.POST, entity, String.class);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response.getBody(), new TypeReference<CompletionRequest>() {});
    }

}
