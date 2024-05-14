package com.example.StudyWithMe.responses.exercise;

import com.example.StudyWithMe.models.exercise.question.Question;
import com.example.StudyWithMe.models.exercise.subject.Subject;
import com.example.StudyWithMe.responses.user.profile.UserResponse;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class QuestionResponse {

    private Long questionId;
    private UserResponse asker;
    private SubjectResponse subject;
    private String title;
    private String content;
    private List<AttachmentResponse> attachments;
    private LocalDateTime createdAt;
    private List<AnswerResponse> answers;

    public static QuestionResponse fromQuestion(UserResponse asker, Question question) {
        return QuestionResponse.builder()
                .questionId(question.getQuestionId())
                .asker(asker)
                .subject(SubjectResponse.fromSubject(question.getSubject()))
                .title(question.getTitle())
                .content(question.getContent())
                .attachments(question.getAttachments().stream().map(AttachmentResponse::fromQuestionAttachment).collect(Collectors.toList()))
                .createdAt(question.getCreatedAt())
                .answers(question.getAnswers() != null ? question.getAnswers().stream().map(AnswerResponse::fromAnswer).collect(Collectors.toList()) : new ArrayList<>())
                .build();
    }
}
