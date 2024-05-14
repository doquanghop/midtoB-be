package com.example.StudyWithMe.models.socialmedia.comment;
import com.example.StudyWithMe.models.BaseEntity;
import com.example.StudyWithMe.models.FileType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "comment_attachments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentAttachment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attachment_id")
    private Long attachmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @Column(name = "attachment_type", length = 20)
    private FileType attachmentType;

    @Column(name = "attachment_url", columnDefinition = "TEXT")
    private String attachmentUrl;
}