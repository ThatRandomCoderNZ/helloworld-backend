package com.helloworld.learn.app.controllers;

import com.helloworld.learn.app.models.features.Progress;
import com.helloworld.learn.app.models.features.ProgressResponse;
import com.helloworld.learn.app.models.features.Vocabulary;
import com.helloworld.learn.app.models.user.DAOUser;
import com.helloworld.learn.app.models.user.UserRoles;
import com.helloworld.learn.app.services.ContentService;
import com.helloworld.learn.app.services.JwtUserDetailsService;
import com.helloworld.learn.app.services.ProgressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProgressController {

    private final ProgressService progressService;
    private final ContentService contentService;

    private final JwtUserDetailsService userService;

    public ProgressController(ProgressService progressService, ContentService contentService, JwtUserDetailsService userService)
    {
        this.progressService = progressService;
        this.contentService = contentService;
        this.userService = userService;
    }

    @GetMapping("/{userUuid}/progress/{wordId}")
    public ProgressResponse getWordProgressForUser(@PathVariable("userUuid") String userUuid, @PathVariable("wordId") Long wordId)
    {
        Progress foundProgress = this.progressService.getProgressByIds(userUuid, wordId);
        return new ProgressResponse(foundProgress);
    }

    @GetMapping("/{userUuid}/progress/grouped")
    public List<ProgressResponse> getAllProgressForWords(
            @PathVariable("userUuid") String userUuid,
            @RequestParam("wordIds") List<Long> wordIds
    )  {
        List<Progress> progress = this.progressService.getAllProgressByIds(userUuid, wordIds);
        return progress.stream().map(ProgressResponse::new).toList();
    }

    @GetMapping("/{userUuid}/progress/lesson/{lessonId}")
    public List<ProgressResponse> getProgressForLesson(
            @PathVariable("userUuid") String userUuid,
            @PathVariable("lessonId") Long lessonId
    ) {
        List<Long> vocabularyIds = this.contentService.getLesson(lessonId).getVocabulary()
                .stream().map(Vocabulary::getVocabularyId).toList();
        List<Progress> progress = this.progressService.getAllProgressByIds(userUuid, vocabularyIds);
        return progress.stream().map(ProgressResponse::new).toList();
    }


    @PostMapping("/{userUuid}/progress/{wordId}")
    public void updateProgress(
            @PathVariable("userUuid") String userUuid,
            @PathVariable("wordId") Long wordId,
            @RequestBody Progress updateProgressRequest)
    {
        Progress progress = progressService.getProgressByIds(userUuid, wordId);
        if(progress != null) {
            progress.setProgress(updateProgressRequest.getProgress());
            this.progressService.upsertProgress(progress);
        }else {
            Vocabulary vocab = this.contentService.getVocabulary(wordId);
            DAOUser user = new DAOUser(userService.findUserByUuid(userUuid).getId(), "", "", UserRoles.USER, "");
            updateProgressRequest.setVocabulary(vocab);
            updateProgressRequest.setUser(user);
            this.progressService.upsertProgress(updateProgressRequest);
        }
    }

}
