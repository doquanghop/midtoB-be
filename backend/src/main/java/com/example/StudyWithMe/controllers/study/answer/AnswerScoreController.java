package com.example.StudyWithMe.controllers.study.answer;

import com.example.StudyWithMe.responses.ResponseObject;
import com.example.StudyWithMe.services.study.answer.IAnswerScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/${api.prefix}/answerScore")
@RequiredArgsConstructor
public class AnswerScoreController {
    private final IAnswerScoreService answerScoreService;
    @PutMapping("/{answerId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> updateScore(
            @PathVariable Long answerId,
            @RequestParam int score){
        answerScoreService.updateAnswerScore(answerId,score);
        return ResponseEntity.ok(ResponseObject.success(HttpStatus.OK,"Update score successfully!",null));
    }
}
