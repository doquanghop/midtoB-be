package com.example.StudyWithMe.responses.socialmedia.comment;

import com.example.StudyWithMe.models.socialmedia.comment.Comment;
import com.example.StudyWithMe.models.socialmedia.comment.CommentAttachment;
import com.example.StudyWithMe.models.user.auth.User;
import com.example.StudyWithMe.models.user.profile.Profile;
import com.example.StudyWithMe.responses.user.profile.ProfileCardResponse;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class CommentResponse {
    private Long commentId;
    private ProfileCardResponse user;
    private String content;
    private List<String> attachments;
    private boolean currentUserLikeComment;
    private Long totalLikes;
    private Long totalComments;
    private LocalDateTime createdAt;
    public static CommentResponse fromComment(User user, Profile profile, Comment comment, List<CommentAttachment> attachments){
        return CommentResponse.builder()
                .commentId(comment.getCommentId())
                .user(ProfileCardResponse.fromUserCard(user,profile))
                .content(comment.getContent())
                .attachments(attachments!= null && !attachments.isEmpty() ? attachments.stream().map(CommentAttachment::getAttachmentUrl).collect(Collectors.toList()) : new ArrayList<>())
                .createdAt(comment.getCreatedAt())
                .build();
    }

}
