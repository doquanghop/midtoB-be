package com.example.StudyWithMe.models.exercise.answer;

import com.example.StudyWithMe.models.FileType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "answer_attachments")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attachment_id")
    private Long attachmentId;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;

    @NotNull
    @Column(name = "file_type")
    private FileType fileType;

    @NotNull
    @Column(name = "file_url")
    private String fileUrl;

}