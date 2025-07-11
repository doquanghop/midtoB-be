package com.example.StudyWithMe.responses.exercise;

import com.example.StudyWithMe.models.FileType;
import com.example.StudyWithMe.models.study.answer.AnswerAttachment;
import com.example.StudyWithMe.models.study.question.QuestionAttachment;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttachmentResponse {

    private Long attachmentId;
    private FileType fileType;
    private String fileUrl;

    public static AttachmentResponse fromQuestionAttachment(QuestionAttachment attachment) {
        return AttachmentResponse.builder()
                .attachmentId(attachment.getAttachmentId())
                .fileType(attachment.getFileType())
                .fileUrl(attachment.getFileUrl())
                .build();
    }

    public static AttachmentResponse fromAnswerAttachment(AnswerAttachment attachment) {
        return AttachmentResponse.builder()
                .attachmentId(attachment.getAttachmentId())
                .fileType(attachment.getFileType())
                .fileUrl(attachment.getFileUrl())
                .build();
    }
}
