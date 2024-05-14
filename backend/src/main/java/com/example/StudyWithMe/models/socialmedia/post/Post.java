package com.example.StudyWithMe.models.socialmedia.post;

import com.example.StudyWithMe.models.BaseEntity;
import com.example.StudyWithMe.models.user.auth.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "posts")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostAttachment> attachments;
}