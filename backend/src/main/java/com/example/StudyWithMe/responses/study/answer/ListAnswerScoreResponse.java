package com.example.StudyWithMe.responses.exercise.answer;

import lombok.Builder;
import lombok.Data;
import java.util.List;
@Data
@Builder
public class ListAnswerScoreResponse {
    private int totalScore;
    private List<AnswerScoreResponse> scores;
}
