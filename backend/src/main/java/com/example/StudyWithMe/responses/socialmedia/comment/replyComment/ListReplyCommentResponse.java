package com.example.StudyWithMe.responses.socialmedia.comment.replyComment;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ListReplyCommentResponse {
    private Long totalPages;
    private List<ReplyCommentResponse> replyComments;
}
