package com.example.StudyWithMe.controllers.socailmedia.comment;

import com.example.StudyWithMe.dataTransferObjects.socialmedia.comment.CommentDTO;
import com.example.StudyWithMe.models.socialmedia.post.Post;
import com.example.StudyWithMe.responses.ResponseObject;
import com.example.StudyWithMe.responses.socialmedia.comment.CommentResponse;
import com.example.StudyWithMe.responses.socialmedia.comment.ListCommentResponse;
import com.example.StudyWithMe.services.socialmedia.comment.ICommentService;
import com.example.StudyWithMe.services.socialmedia.post.IPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {
    private final ICommentService commentService;
    private final IPostService postService;
    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> createComment(
            @RequestParam Long postId,
            @ModelAttribute CommentDTO commentDTO
    ){
        Post post = postService.getPostDetail(postId);
        CommentResponse newComment = commentService.createComment(post, commentDTO);
        return ResponseEntity.ok(ResponseObject.success(HttpStatus.OK,"Create comment successfully",newComment));
    }
    @DeleteMapping("")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> deleteComment(
            @RequestParam Long commentId
    ){;
        commentService.deleteComment(commentId);
        return ResponseEntity.ok(ResponseObject.success(HttpStatus.OK,"Delete comment successfully!",""));
    }
    @GetMapping("")
    public ResponseEntity<?> getAllComment(
            @RequestParam Long postId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit
    ){
        ListCommentResponse commentResponse = commentService.getAllCommentForPost(postId,page,limit);
        return ResponseEntity.ok(ResponseObject.success(HttpStatus.OK,"Get comment successfully!",commentResponse));
    }
}
