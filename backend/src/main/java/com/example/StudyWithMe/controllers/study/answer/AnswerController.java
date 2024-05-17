package com.example.StudyWithMe.controllers.study.answer;

import com.example.StudyWithMe.dataTransferObjects.study.AnswerDTO;
import com.example.StudyWithMe.models.study.question.Question;
import com.example.StudyWithMe.responses.ResponseObject;
import com.example.StudyWithMe.responses.study.answer.AnswerResponse;
import com.example.StudyWithMe.responses.study.answer.ListAnswerResponse;
import com.example.StudyWithMe.services.study.answer.IAnswerService;
import com.example.StudyWithMe.services.study.question.IQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/${api.prefix}/answer")
@RequiredArgsConstructor
public class AnswerController {

    private final IAnswerService answerService;
    private final IQuestionService questionService;

    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> createAnswer(@ModelAttribute AnswerDTO answerDTO) {
        Question question = questionService.getQuestionDetail(answerDTO.getQuestionId());
        AnswerResponse newAnswer = answerService.createAnswer(answerDTO, question);
        return ResponseEntity.ok(ResponseObject.success(HttpStatus.OK, "Create answer successfully", newAnswer));
    }

    @GetMapping("")
    public ResponseEntity<?> getAnswersByQuestionId(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size,
                                                    @RequestParam Long questionId) {
        PageRequest pageRequest = PageRequest.of(page, size);
        ListAnswerResponse answerResponses = answerService.getAnswersByQuestionId(questionId, pageRequest);
        return ResponseEntity.ok(ResponseObject.success(HttpStatus.OK, "Get answers by questionId = " + questionId, answerResponses));
    }

    @DeleteMapping("")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> deleteAnswer(@RequestParam Long answerId) {
        answerService.deleteAnswer(answerId);
        return ResponseEntity.ok(ResponseObject.success(HttpStatus.OK, "Delete answer successfully", null));
    }
}
