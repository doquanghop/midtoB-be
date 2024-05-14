package com.example.StudyWithMe.models.socialmedia.comment;

import com.example.StudyWithMe.models.BaseEntity;
import com.example.StudyWithMe.models.socialmedia.post.Post;
import com.example.StudyWithMe.models.user.auth.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    private List<CommentAttachment> attachments;

}