package com.example.StudyWithMe.controllers.exercise;

import com.example.StudyWithMe.dataTransferObjects.exercise.AnswerDTO;
import com.example.StudyWithMe.models.exercise.question.Question;
import com.example.StudyWithMe.responses.ResponseObject;
import com.example.StudyWithMe.responses.exercise.answer.AnswerResponse;
import com.example.StudyWithMe.services.exercise.answer.IAnswerScoreService;
import com.example.StudyWithMe.services.exercise.answer.IAnswerService;
import com.example.StudyWithMe.services.exercise.question.IQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
