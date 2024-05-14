package com.example.StudyWithMe.models.socialmedia.like;

import com.example.StudyWithMe.models.socialmedia.post.Post;
import com.example.StudyWithMe.models.user.auth.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "likes")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long likeId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}