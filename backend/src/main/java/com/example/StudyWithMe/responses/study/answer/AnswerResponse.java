package com.example.StudyWithMe.responses.exercise.answer;

import com.example.StudyWithMe.models.study.answer.Answer;
import com.example.StudyWithMe.responses.exercise.AttachmentResponse;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class AnswerResponse {

    private Long answerId;
    private Long userId;
    private String content;
    private List<AttachmentResponse> attachments;
    private ListAnswerScoreResponse answerScores;
    private LocalDateTime createdAt;

    public static AnswerResponse fromAnswer(Answer answer) {
        return AnswerResponse.builder()
                .answerId(answer.getAnswerId())
                .userId(answer.getUser().getUserId())
                .content(answer.getContent())
                .attachments(answer.getAttachments().stream().map(AttachmentResponse::fromAnswerAttachment).collect(Collectors.toList()))
                .createdAt(answer.getCreatedAt())
                .build();
    }
}
