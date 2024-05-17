package com.example.StudyWithMe.responses.exercise.question;

import com.example.StudyWithMe.models.study.question.Question;
import com.example.StudyWithMe.responses.exercise.AttachmentResponse;
import com.example.StudyWithMe.responses.exercise.SubjectResponse;
import com.example.StudyWithMe.responses.exercise.answer.AnswerResponse;
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
