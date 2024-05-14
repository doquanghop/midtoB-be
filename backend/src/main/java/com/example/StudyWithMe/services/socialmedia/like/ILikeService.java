package com.example.StudyWithMe.services.socialmedia.like;

import com.example.StudyWithMe.models.socialmedia.like.Like;
import com.example.StudyWithMe.models.socialmedia.post.Post;
import com.example.StudyWithMe.responses.socialmedia.like.LikeResponse;

public interface ILikeService {
    Like likePost(Post post);
    void unlikePost(Post post);
    LikeResponse getTotalLikeForPost(Post post);
    void deleteAllLikeForPost(Post post);
}
