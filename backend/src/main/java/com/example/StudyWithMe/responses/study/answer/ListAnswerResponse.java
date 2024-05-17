package com.example.StudyWithMe.responses.exercise.answer;

import lombok.Builder;
import lombok.Data;
import java.util.List;
@Data
@Builder
public class ListAnswerResponse {
    private int totalPages;
    private List<AnswerResponse> answers;
}
