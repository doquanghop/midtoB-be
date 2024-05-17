package com.example.StudyWithMe.controllers.study.question;

import com.example.StudyWithMe.dataTransferObjects.study.QuestionDTO;
import com.example.StudyWithMe.models.study.question.Question;
import com.example.StudyWithMe.models.study.category.Category;
import com.example.StudyWithMe.models.user.auth.User;
import com.example.StudyWithMe.responses.ResponseObject;
import com.example.StudyWithMe.responses.study.question.ListQuestionResponse;
import com.example.StudyWithMe.responses.study.question.QuestionResponse;
import com.example.StudyWithMe.services.study.question.IQuestionService;
import com.example.StudyWithMe.services.study.category.ICategoryService;
import com.example.StudyWithMe.services.user.auth.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/${api.prefix}/question")
@RequiredArgsConstructor
public class QuestionController {

    private final IQuestionService questionService;
    private final IAuthService authService;

    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> createQuestion(@ModelAttribute QuestionDTO questionDTO) {
        User asker = authService.getCurrentUser();
        QuestionResponse newQuestion = questionService.createQuestion(asker,questionDTO);
        return ResponseEntity.ok(ResponseObject.success(HttpStatus.OK, "Create question successfully", newQuestion));
    }

    @GetMapping("")
    public ResponseEntity<?> getAllQuestions(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        ListQuestionResponse questionResponses = questionService.getAllQuestion(pageRequest);
        return ResponseEntity.ok(ResponseObject.success(HttpStatus.OK, "Get all questions", questionResponses));
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<?> getQuestionDetail(@PathVariable Long questionId) {
        Question question = questionService.getQuestionDetail(questionId);
        if (question == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ResponseObject.success(HttpStatus.OK, "Get questions", question));
    }




    @DeleteMapping("")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> deleteQuestion(@RequestParam Long questionId) {
        questionService.deleteQuestion(questionId);
        return ResponseEntity.ok(ResponseObject.success(HttpStatus.OK, "Delete question successfully", null));
    }
}
