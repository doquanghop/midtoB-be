package com.example.StudyWithMe.models.socialmedia.comment;
import com.example.StudyWithMe.models.BaseEntity;
import com.example.StudyWithMe.models.user.auth.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "reply_comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyComment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_comment_id")
    private Long replyCommentId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;
    @OneToMany(mappedBy = "replyComment", cascade = CascadeType.ALL)
    private List<ReplyCommentAttachment> attachments;
}