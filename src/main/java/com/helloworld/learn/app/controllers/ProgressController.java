package com.helloworld.learn.app.controllers;

import com.helloworld.learn.app.models.features.Progress;
import com.helloworld.learn.app.models.features.ProgressResponse;
import com.helloworld.learn.app.models.features.Vocabulary;
import com.helloworld.learn.app.models.user.DAOUser;
import com.helloworld.learn.app.requests.UpdateProgressRequest;
import com.helloworld.learn.app.services.ContentService;
import com.helloworld.learn.app.services.ProgressService;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class ProgressController {

    private final ProgressService progressService;
    private final ContentService contentService;

    public ProgressController(ProgressService progressService, ContentService contentService)
    {
        this.progressService = progressService;
        this.contentService = contentService;
    }

    @GetMapping("/{userId}/progress/{wordId}")
    public ProgressResponse getWordProgressForUser(@PathVariable("userId") Long userId, @PathVariable("wordId") Long wordId)
    {
        Progress foundProgress = this.progressService.getProgressByIds(userId, wordId);
        return new ProgressResponse(foundProgress);
    }

    @GetMapping("/{userId}/progress/grouped")
    public List<ProgressResponse> getAllProgressForWords(
            @PathVariable("userId") Long userId,
            @RequestParam("wordIds") List<Long> wordIds
    )  {
        List<Progress> progress = this.progressService.getAllProgressByIds(userId, wordIds);
        return progress.stream().map(ProgressResponse::new).toList();
    }

    @GetMapping("/{userId}/progress/lesson/{lessonId}")
    public List<ProgressResponse> getProgressForLesson(
            @PathVariable("userId") Long userId,
            @PathVariable("lessonId") Long lessonId
    ) {
        List<Long> vocabularyIds = this.contentService.getLesson(lessonId).getVocabulary()
                .stream().map(Vocabulary::getVocabularyId).toList();
        List<Progress> progress = this.progressService.getAllProgressByIds(userId, vocabularyIds);
        return progress.stream().map(ProgressResponse::new).toList();
    }


    @PostMapping("/{userId}/progress/{wordId}")
    public void updateProgress(
            @PathVariable("userId") Long userId,
            @PathVariable("wordId") Long wordId,
            @RequestBody Progress updateProgressRequest)
    {
        Progress progress = progressService.getProgressByIds(userId, wordId);
        if(progress != null) {
            progress.setProgress(updateProgressRequest.getProgress());
            this.progressService.upsertProgress(progress);
        }else {
            Vocabulary vocab = this.contentService.getVocabulary(wordId);
            DAOUser user = new DAOUser(userId, "", "");
            updateProgressRequest.setVocabulary(vocab);
            updateProgressRequest.setUser(user);
            this.progressService.upsertProgress(updateProgressRequest);
        }
    }

}
