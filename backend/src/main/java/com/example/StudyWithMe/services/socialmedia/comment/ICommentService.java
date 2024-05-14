package com.example.StudyWithMe.services.socialmedia.comment;

import com.example.StudyWithMe.dataTransferObjects.socialmedia.comment.CommentDTO;
import com.example.StudyWithMe.models.socialmedia.comment.Comment;
import com.example.StudyWithMe.models.socialmedia.post.Post;
import com.example.StudyWithMe.responses.socialmedia.comment.CommentResponse;
import com.example.StudyWithMe.responses.socialmedia.comment.ListCommentResponse;

public interface ICommentService {
    CommentResponse createComment(Post post, CommentDTO commentDTO);

    void deleteComment(Long commentId);
    void deleteAllCommentForPost(Post post);

    ListCommentResponse getAllCommentForPost(Long postId,int page,int limit);
    Comment getComment(Long commentId);
    long countByPostId(Long postId);
}
