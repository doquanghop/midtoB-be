package com.example.StudyWithMe.controllers.socailmedia;

import com.example.StudyWithMe.dataTransferObjects.socialmedia.post.PostDTO;
import com.example.StudyWithMe.models.socialmedia.post.Post;
import com.example.StudyWithMe.responses.ResponseObject;
import com.example.StudyWithMe.responses.socialmedia.post.PostResponse;
import com.example.StudyWithMe.services.socialmedia.post.IPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/${api.prefix}/post")
@RequiredArgsConstructor
public class PostController {

    private final IPostService postService;
    @PostMapping("/createPost")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> createPost(@ModelAttribute PostDTO post) {
        PostResponse newPost = postService.createPost(post);
        return ResponseEntity.ok(ResponseObject.success(HttpStatus.OK,"Create post successully",newPost));
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllPost(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit
    ) {
        List<PostResponse> postResponses = postService.getAllPost(page,limit);
        return ResponseEntity.ok(ResponseObject.success(HttpStatus.OK,"Get all post",postResponses));
    }
    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostDetail(
            @PathVariable Long postId) {
        Post post = postService.getPostDetail(postId);
        if (post == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(post);
    }
    @DeleteMapping("")
    public ResponseEntity<?> deletePost(
            @RequestParam Long postId
    ){
        postService.deletePost(postId);
        return ResponseEntity.ok(ResponseObject.success(HttpStatus.OK,"Delete post successfully",null));
    }
}
