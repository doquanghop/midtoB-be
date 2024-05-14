package com.example.StudyWithMe.services.exercise.answer;

import com.example.StudyWithMe.dataTransferObjects.exercise.AnswerDTO;
import com.example.StudyWithMe.models.exercise.question.Question;
import com.example.StudyWithMe.responses.exercise.AnswerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IAnswerService {

    AnswerResponse createAnswer(AnswerDTO answerDTO, Question question);
    Page<AnswerResponse> getAnswersByQuestionId(Long questionId, PageRequest pageRequest);
    void deleteAnswer(Long answerId);
}
