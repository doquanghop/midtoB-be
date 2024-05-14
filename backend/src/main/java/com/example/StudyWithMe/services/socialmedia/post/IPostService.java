package com.example.StudyWithMe.services.socialmedia.post;

import com.example.StudyWithMe.dataTransferObjects.socialmedia.post.PostDTO;
import com.example.StudyWithMe.models.socialmedia.post.Post;
import com.example.StudyWithMe.responses.socialmedia.post.PostResponse;
import org.springframework.data.domain.PageRequest;

import java.util.List;
public interface IPostService {
    PostResponse createPost(PostDTO postDTO);
    List<PostResponse> getAllPost(int page,int limit);

    Post getPostDetail(Long postId);
    void deletePost(Long postId);
}
