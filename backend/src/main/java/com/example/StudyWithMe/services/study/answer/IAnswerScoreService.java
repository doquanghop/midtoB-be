package com.example.StudyWithMe.services.exercise.answer;

import com.example.StudyWithMe.models.study.answer.AnswerScore;
import com.example.StudyWithMe.responses.exercise.answer.ListAnswerScoreResponse;

public interface IAnswerScoreService {
    AnswerScore createRateAnswer(Long answerId);
    ListAnswerScoreResponse getAllAnswerScore(Long answerId);
    void updateAnswerScore(Long answerId,int score);
}
