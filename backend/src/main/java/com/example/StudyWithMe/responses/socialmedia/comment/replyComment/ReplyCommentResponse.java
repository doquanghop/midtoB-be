package com.example.StudyWithMe.responses.socialmedia.comment.replyComment;

import com.example.StudyWithMe.models.socialmedia.comment.ReplyComment;
import com.example.StudyWithMe.models.socialmedia.comment.ReplyCommentAttachment;
import com.example.StudyWithMe.models.user.auth.User;
import com.example.StudyWithMe.models.user.profile.Profile;
import com.example.StudyWithMe.responses.user.profile.ProfileCardResponse;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class ReplyCommentResponse {
    private Long replyCommentId;
    private ProfileCardResponse user;
    private String content;
    private List<String> attachments;
    public static ReplyCommentResponse fromReplyComment(User user, Profile profile, ReplyComment replyComment, List<ReplyCommentAttachment> attachments){
        return ReplyCommentResponse.builder()
                .replyCommentId(replyComment.getReplyCommentId())
                .user(ProfileCardResponse.fromUserCard(user,profile))
                .content(replyComment.getContent())
                .attachments(attachments != null && !attachments.isEmpty() ? attachments.stream().map(ReplyCommentAttachment::getAttachmentUrl).collect(Collectors.toList()) : new ArrayList<>())
                .build();
    }
}
