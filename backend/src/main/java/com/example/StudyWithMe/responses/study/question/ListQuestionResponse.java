package com.example.StudyWithMe.responses.exercise.question;

import lombok.Builder;
import lombok.Data;
import java.util.List;
@Data
@Builder
public class ListQuestionResponse {
    private int totalPages;
    private List<QuestionResponse> questions;
}
