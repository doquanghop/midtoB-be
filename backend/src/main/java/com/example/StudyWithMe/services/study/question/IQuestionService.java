package com.example.StudyWithMe.services.exercise.question;

import com.example.StudyWithMe.dataTransferObjects.study.QuestionDTO;
import com.example.StudyWithMe.models.study.question.Question;
import com.example.StudyWithMe.models.study.subject.Subject;
import com.example.StudyWithMe.responses.exercise.question.ListQuestionResponse;
import com.example.StudyWithMe.responses.exercise.question.QuestionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IQuestionService {

    QuestionResponse createQuestion(QuestionDTO questionDTO, Subject subject);
    ListQuestionResponse getAllQuestion(PageRequest pageRequest);
    Question getQuestionDetail(Long questionId);
    Page<QuestionResponse> getQuestionsByUserId(Long userId, PageRequest pageRequest);
    Page<QuestionResponse> getQuestionsBySubjectId(Long subjectId, PageRequest pageRequest);
    void deleteQuestion(Long questionId);
}
