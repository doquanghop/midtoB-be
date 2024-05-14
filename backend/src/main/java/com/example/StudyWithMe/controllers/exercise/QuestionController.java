package com.example.StudyWithMe.controllers.exercise;

import com.example.StudyWithMe.dataTransferObjects.exercise.QuestionDTO;
import com.example.StudyWithMe.models.exercise.question.Question;
import com.example.StudyWithMe.models.exercise.subject.Subject;
import com.example.StudyWithMe.responses.ResponseObject;
import com.example.StudyWithMe.responses.exercise.QuestionResponse;
import com.example.StudyWithMe.services.exercise.question.IQuestionService;
import com.example.StudyWithMe.services.exercise.subject.ISubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/${api.prefix}/question")
@RequiredArgsConstructor
public class QuestionController {

    private final IQuestionService questionService;
    private final ISubjectService subjectService;

    @PostMapping("/create")
    public ResponseEntity<?> createQuestion(@ModelAttribute QuestionDTO questionDTO) {
        Subject subject = subjectService.findById(questionDTO.getSubjectId());
        QuestionResponse newQuestion = questionService.createQuestion(questionDTO, subject);
        return ResponseEntity.ok(ResponseObject.success(HttpStatus.OK, "Create question successfully", newQuestion));
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllQuestions(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<QuestionResponse> questionResponses = questionService.getAllQuestion(pageRequest);
        return ResponseEntity.ok(ResponseObject.success(HttpStatus.OK, "Get all questions", questionResponses));
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<?> getQuestionDetail(@PathVariable Long questionId) {
        Question question = questionService.getQuestionDetail(questionId);
        if (question == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(question);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getQuestionsByUserId(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size,
                                                  @PathVariable Long userId) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<QuestionResponse> questionResponses = questionService.getQuestionsByUserId(userId, pageRequest);
        return ResponseEntity.ok(ResponseObject.success(HttpStatus.OK, "Get questions by userId = " + userId, questionResponses));
    }

    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<?> getQuestionsBySubjectId(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size,
                                                  @PathVariable Long subjectId) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<QuestionResponse> questionResponses = questionService.getQuestionsBySubjectId(subjectId, pageRequest);
        return ResponseEntity.ok(ResponseObject.success(HttpStatus.OK, "Get questions by subjectId = " + subjectId, questionResponses));
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteQuestion(@RequestParam Long questionId) {
        questionService.deleteQuestion(questionId);
        return ResponseEntity.ok(ResponseObject.success(HttpStatus.OK, "Delete question successfully", null));
    }
}
