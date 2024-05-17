package com.example.StudyWithMe.services.exercise.answer;

import com.example.StudyWithMe.dataTransferObjects.study.AnswerDTO;
import com.example.StudyWithMe.models.study.question.Question;
import com.example.StudyWithMe.responses.exercise.answer.AnswerResponse;
import com.example.StudyWithMe.responses.exercise.answer.ListAnswerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IAnswerService {

    AnswerResponse createAnswer(AnswerDTO answerDTO, Question question);
    ListAnswerResponse getAnswersByQuestionId(Long questionId, PageRequest pageRequest);
    void deleteAnswer(Long answerId);
}
