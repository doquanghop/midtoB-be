package com.example.StudyWithMe.controllers.socailmedia;

import com.example.StudyWithMe.models.socialmedia.like.Like;
import com.example.StudyWithMe.models.socialmedia.post.Post;
import com.example.StudyWithMe.responses.ResponseObject;
import com.example.StudyWithMe.services.socialmedia.like.ILikeService;
import com.example.StudyWithMe.services.socialmedia.post.IPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/${api.prefix}/like")
@RequiredArgsConstructor
public class LikeController {
    private final ILikeService likeService;
    private final IPostService postService;
    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> likePost(@RequestParam Long postId) {
        Post existingPost = postService.getPostDetail(postId);
        Like newLike = likeService.likePost(existingPost);
        return ResponseEntity.ok(ResponseObject.success(HttpStatus.OK,"Post liked successfully",null));
    }
    @DeleteMapping("/unlike")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> unlikePost(@RequestParam Long postId) {
        Post existingPost = postService.getPostDetail(postId);
        likeService.unlikePost(existingPost);
        return ResponseEntity.ok(ResponseObject.success(HttpStatus.OK,"Post unliked successfully",null));
    }
}
