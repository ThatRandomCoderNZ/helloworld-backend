package com.helloworld.learn.app.services;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.RecognizeResponse;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.texttospeech.v1.*;
import com.google.protobuf.ByteString;
import com.helloworld.learn.app.models.features.Language;
import com.helloworld.learn.app.repositories.LanguageRepository;
import org.apache.commons.codec.language.bm.Lang;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.stream.Collectors;

@Service
public class GoogleApiInteractionService {


    private final Logger logger;

    private final LanguageRepository languageRepository;

    public GoogleApiInteractionService(LanguageRepository languageRepository){
        this.languageRepository = languageRepository;
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    public byte[] convertTextToSpeech(String promptText, Long languageId) throws IOException {
        System.out.println("Pretty sure the next step fails without auth");
        String credentialsPath = "/var/app/current/helloworldlearn-378008-dbd2da23740e.json";
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(credentialsPath));
        TextToSpeechSettings textToSpeechSettings = TextToSpeechSettings.newHttpJsonBuilder().build();
        try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create(TextToSpeechSettings.newHttpJsonBuilder().setCredentialsProvider(FixedCredentialsProvider.create(credentials)).build());) {

            Language language = this.languageRepository.findById(languageId).orElseThrow();
            // Set the text input to be synthesized
            SynthesisInput input = SynthesisInput.newBuilder().setText(promptText).build();

            // Build the voice request, select the language code ("en-US") and the ssml voice gender
            // ("neutral")
            VoiceSelectionParams voice =
                    VoiceSelectionParams.newBuilder()
                            .setLanguageCode(language.getCode())
                            .setSsmlGender(SsmlVoiceGender.NEUTRAL)
                            .build();

            // Select the type of audio file you want returned
            AudioConfig audioConfig =
                    AudioConfig.newBuilder().setAudioEncoding(AudioEncoding.MP3).build();

            // Perform the text-to-speech request on the text input with the selected voice parameters and
            // audio file type
            SynthesizeSpeechResponse response =
                    textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);

            System.out.println("Success");
            // Get the audio contents from the response
            ByteString audioContents = response.getAudioContent();

            return audioContents.toByteArray();
        }
    }


    public String convertSpeechToText(MultipartFile audioFile, Long languageId) throws IOException
    {
        logger.warn(String.valueOf(audioFile.getBytes().length));
        try (SpeechClient speechClient = SpeechClient.create()){
            // Transcribe the audio using the Speech-to-Text API
            Language language = languageRepository.findById(languageId).orElseThrow();

            RecognitionConfig recognitionConfig = RecognitionConfig.newBuilder()
                    .setEncoding(RecognitionConfig.AudioEncoding.WEBM_OPUS)
                    .setSampleRateHertz(48000)
                    .setAudioChannelCount(1)
                    .setLanguageCode(language.getCode())
                    .setModel("latest_short")
                    .build();
            RecognitionAudio recognitionAudio = RecognitionAudio.newBuilder()
                    .setContent(ByteString.copyFrom(audioFile.getBytes()))
                    .build();
            RecognizeResponse response = speechClient.recognize(recognitionConfig, recognitionAudio);

            logger.warn("Response: " + response.toString());
            String transcript = response.getResultsList().stream()
                    .map(result -> result.getAlternativesList().get(0).getTranscript())
                    .collect(Collectors.joining());



            logger.warn("Transcript: " + transcript);
            return transcript;
        }
    }

}
