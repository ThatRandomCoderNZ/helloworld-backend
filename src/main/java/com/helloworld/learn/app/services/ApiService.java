package com.helloworld.learn.app.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helloworld.learn.app.models.openai.CompletionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.vault.core.VaultKeyValueOperationsSupport;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponse;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {

    @Value("${openai.key}")
    private String apiKey;


    @Autowired
    private VaultTemplate vaultTemplate;

    public CompletionRequest makeRequest(String prompt) throws JsonProcessingException {
        // You usually would not print a secret to stdout
//        VaultResponse vaultResponse = vaultTemplate
//                .opsForKeyValue("secret", VaultKeyValueOperationsSupport.KeyValueBackend.KV_2).get("openai");
//
//        apiKey = vaultResponse.getData().get("openai.oauth2.key").toString();

        RestTemplate restTemplate = new RestTemplate();
        String resource = "https://api.openai.com/v1/chat/completions";
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        //String prompt = "Does the following spanish sentence have a noun and verb and correct grammar: 'Hago pizza'. Give true/false.";
        String requestJson = "{\"model\": \"gpt-3.5-turbo\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}], \"temperature\": 0.7}";

        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        ResponseEntity<String> response = restTemplate.exchange(resource, HttpMethod.POST, entity, String.class);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response.getBody(), new TypeReference<CompletionRequest>() {});
    }

}
