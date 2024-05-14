package com.example.StudyWithMe.models.socialmedia.post;

import com.example.StudyWithMe.models.BaseEntity;
import com.example.StudyWithMe.models.FileType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "post_attachments")
@Builder
public class PostAttachment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attachment_id")
    private Long attachmentId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "attachment_type")
    private String attachmentType;

    @Column(name = "attachment_url", columnDefinition = "TEXT")
    private String attachmentUrl;

}
