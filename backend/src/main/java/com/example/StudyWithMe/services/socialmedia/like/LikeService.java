package com.example.StudyWithMe.services.socialmedia.like;

import com.example.StudyWithMe.models.socialmedia.like.Like;
import com.example.StudyWithMe.models.socialmedia.post.Post;
import com.example.StudyWithMe.models.user.auth.User;
import com.example.StudyWithMe.repositories.socialmedia.like.LikeRepository;
import com.example.StudyWithMe.responses.socialmedia.like.LikeResponse;
import com.example.StudyWithMe.services.user.auth.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService implements ILikeService {
    private final LikeRepository likeRepository;
    private final IAuthService authService;
    @Override
    public Like likePost(Post post) {
        User currentUser = authService.getCurrentUser();
        Like newLike = Like.builder()
                .post(post)
                .user(currentUser)
                .build();
        likeRepository.save(newLike);
        return newLike;
    }

    @Override
    public void unlikePost(Post post) {
        User currentUser = authService.getCurrentUser();
        likeRepository.deleteByUserAndPost(currentUser.getUserId(),post.getPostId());
    }
    @Override
    public LikeResponse getTotalLikeForPost(Post post) {
        long totalLike = this.countByPostId(post.getPostId());
        User currentUser = authService.getCurrentUser();
        if (currentUser == null){
            return LikeResponse.fromLike(totalLike,false);
        }
        return LikeResponse.fromLike(totalLike,hasUserLikedPost(currentUser, post));
    }
    @Override
    public void deleteAllLikeForPost(Post post){
        likeRepository.deleteAllLikeForPost(post.getPostId());
    }

    public boolean hasUserLikedPost(User user, Post post) {
        User currentUser = authService.getCurrentUser();
        return likeRepository.existsByUserAndPost(currentUser, post);
    }
    private long countByPostId(Long postId) {
        return likeRepository.countByPostId(postId);
    }
}
