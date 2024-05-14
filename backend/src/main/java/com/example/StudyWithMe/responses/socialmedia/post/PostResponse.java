package com.example.StudyWithMe.responses.socialmedia.post;

import com.example.StudyWithMe.models.socialmedia.post.Post;
import com.example.StudyWithMe.responses.socialmedia.like.LikeResponse;
import com.example.StudyWithMe.responses.user.profile.ProfileCardResponse;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class PostResponse {
    private Long postId;
    private ProfileCardResponse poster;
    private String content;
    private List<String> attachments;
    private LocalDateTime createdAt;
    private boolean currentUserLikePost;
    private Long totalLikes;
    private Long totalComments;
    public static PostResponse fromPost(ProfileCardResponse poster, Post post, LikeResponse likes,Long totalComment, List<String> attachments){
        return PostResponse.builder()
                .postId(post.getPostId())
                .poster(poster)
                .content(post.getContent())
                .attachments(attachments)
                .currentUserLikePost(likes.isLike())
                .totalLikes(likes.getTotalLike())
                .totalComments(totalComment)
                .createdAt(post.getCreatedAt())
                .build();
    }
}
