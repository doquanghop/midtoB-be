package com.example.StudyWithMe.models.exercise.question;

import com.example.StudyWithMe.models.FileType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "question_attachments")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attachment_id")
    private Long attachmentId;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @NotNull
    @Column(name = "file_type")
    private FileType fileType;

    @NotNull
    @Column(name = "file_url")
    private String fileUrl;

}