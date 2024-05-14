package com.example.StudyWithMe.services.exercise.question;

import com.example.StudyWithMe.dataTransferObjects.exercise.QuestionDTO;
import com.example.StudyWithMe.models.exercise.question.Question;
import com.example.StudyWithMe.models.exercise.subject.Subject;
import com.example.StudyWithMe.responses.exercise.QuestionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IQuestionService {

    QuestionResponse createQuestion(QuestionDTO questionDTO, Subject subject);
    Page<QuestionResponse> getAllQuestion(PageRequest pageRequest);
    Question getQuestionDetail(Long questionId);
    Page<QuestionResponse> getQuestionsByUserId(Long userId, PageRequest pageRequest);
    Page<QuestionResponse> getQuestionsBySubjectId(Long subjectId, PageRequest pageRequest);
    void deleteQuestion(Long questionId);
}
