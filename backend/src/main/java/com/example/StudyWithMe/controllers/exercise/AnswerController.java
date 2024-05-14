package com.example.StudyWithMe.controllers.exercise;

import com.example.StudyWithMe.dataTransferObjects.exercise.AnswerDTO;
import com.example.StudyWithMe.models.exercise.question.Question;
import com.example.StudyWithMe.responses.ResponseObject;
import com.example.StudyWithMe.responses.exercise.AnswerResponse;
import com.example.StudyWithMe.services.exercise.answer.IAnswerService;
import com.example.StudyWithMe.services.exercise.question.IQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/${api.prefix}/answer")
@RequiredArgsConstructor
public class AnswerController {

    private final IAnswerService answerService;
    private final IQuestionService questionService;

    @PostMapping("/create")
    public ResponseEntity<?> createAnswer(@ModelAttribute AnswerDTO answerDTO) {
        Question question = questionService.getQuestionDetail(answerDTO.getQuestionId());
        AnswerResponse newAnswer = answerService.createAnswer(answerDTO, question);
        return ResponseEntity.ok(ResponseObject.success(HttpStatus.OK, "Create answer successfully", newAnswer));
    }

    @GetMapping("/question/{questionId}")
    public ResponseEntity<?> getAnswersByQuestionId(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size,
                                                    @PathVariable Long questionId) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<AnswerResponse> answerResponses = answerService.getAnswersByQuestionId(questionId, pageRequest);
        return ResponseEntity.ok(ResponseObject.success(HttpStatus.OK, "Get answers by questionId = " + questionId, answerResponses));
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteAnswer(@RequestParam Long answerId) {
        answerService.deleteAnswer(answerId);
        return ResponseEntity.ok(ResponseObject.success(HttpStatus.OK, "Delete answer successfully", null));
    }
}
