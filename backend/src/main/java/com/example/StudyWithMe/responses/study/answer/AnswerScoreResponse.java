package com.example.StudyWithMe.responses.exercise.answer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnswerScoreResponse {
    private Long userId;
    private int score;
}
