package com.example.StudyWithMe.services.socialmedia.comment;

import com.example.StudyWithMe.dataTransferObjects.socialmedia.comment.ReplyCommentDTO;
import com.example.StudyWithMe.models.socialmedia.comment.Comment;
import com.example.StudyWithMe.responses.socialmedia.comment.replyComment.ListReplyCommentResponse;
import com.example.StudyWithMe.responses.socialmedia.comment.replyComment.ReplyCommentResponse;

public interface ICommentReplySerivce {
    ReplyCommentResponse createReplyComment(Comment comment, ReplyCommentDTO replyCommentDTO);

    void deleteReplyComment(Long replyCommentId);
    void deleteAllReplyCommentForComment(Long commentId);

    ListReplyCommentResponse getAllReplyCommentByComment(Long commentId);
}
