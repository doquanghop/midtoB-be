package com.example.StudyWithMe.controllers.socailmedia.comment;

import com.example.StudyWithMe.dataTransferObjects.socialmedia.comment.ReplyCommentDTO;
import com.example.StudyWithMe.models.socialmedia.comment.Comment;
import com.example.StudyWithMe.responses.ResponseObject;
import com.example.StudyWithMe.responses.socialmedia.comment.replyComment.ListReplyCommentResponse;
import com.example.StudyWithMe.responses.socialmedia.comment.replyComment.ReplyCommentResponse;
import com.example.StudyWithMe.services.socialmedia.comment.ICommentReplySerivce;
import com.example.StudyWithMe.services.socialmedia.comment.ICommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/replyComment")
@RequiredArgsConstructor

public class ReplyCommentController {
    private final ICommentReplySerivce commentReplySerivce;
    private final ICommentService commentService;
    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> createReplyComment(
            @RequestParam Long commentId,
            @ModelAttribute ReplyCommentDTO replyCommentDTO
    ) {
        Comment comment = commentService.getComment(commentId);
        ReplyCommentResponse replyCommentResponse = commentReplySerivce.createReplyComment(comment,replyCommentDTO);
        return ResponseEntity.ok(ResponseObject.success(HttpStatus.OK, "Create reply comment successfully!", replyCommentResponse));

    }
    @DeleteMapping("")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> deleteCommentReply(
            @RequestParam Long replyCommentId
    ){
        commentReplySerivce.deleteReplyComment(replyCommentId);
        return ResponseEntity.ok(ResponseObject.success(HttpStatus.OK,"Delete comment reply successfully!",""));
    }
    @GetMapping("")
    public ResponseEntity<?> getAllReplyComment(
            @RequestParam Long commentId
    ){
        ListReplyCommentResponse commentResponse = commentReplySerivce.getAllReplyCommentByComment(commentId);
        return ResponseEntity.ok(ResponseObject.success(HttpStatus.OK,"Get all reply comment successfully!",commentResponse));
    }
}
