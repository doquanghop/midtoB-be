package com.example.StudyWithMe.responses.exercise;

import com.example.StudyWithMe.models.exercise.answer.Answer;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class AnswerResponse {

    private Long answerId;
    private Long userId;
    private String content;
    private List<AttachmentResponse> attachments;
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
