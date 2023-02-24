package com.helloworld.learn.app.services;

import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.io.IOException;

@Service
public class GoogleCloudSpeechToTextService{

    private final SpeechClient speechClient;

    public GoogleCloudSpeechToTextService() throws IOException {
        speechClient = SpeechClient.create();
    }

    public String transcribeAudio(byte[] audioData) throws IOException {
        ByteString audioBytes = ByteString.copyFrom(audioData);
        RecognitionConfig config = RecognitionConfig.newBuilder()
                .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                .setLanguageCode("en-US")
                .build();
        RecognitionAudio audio = RecognitionAudio.newBuilder()
                .setContent(audioBytes)
                .build();
        RecognizeResponse response = speechClient.recognize(config, audio);
        StringBuilder transcriptionBuilder = new StringBuilder();
        for (SpeechRecognitionResult result : response.getResultsList()) {
            for (SpeechRecognitionAlternative alternative : result.getAlternativesList()) {
                transcriptionBuilder.append(alternative.getTranscript());
            }
        }
        return transcriptionBuilder.toString();
    }

    @PreDestroy
    public void cleanup() {
        speechClient.close();
    }

}
