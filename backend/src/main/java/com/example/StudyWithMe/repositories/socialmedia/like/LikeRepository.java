package com.example.StudyWithMe.repositories.socialmedia.like;

import com.example.StudyWithMe.models.socialmedia.like.Like;
import com.example.StudyWithMe.models.socialmedia.post.Post;
import com.example.StudyWithMe.models.user.auth.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like,Long> {
    List<Like> findAllByPost(Post post);
    @Modifying
    @Transactional
    @Query("DELETE FROM Like l WHERE l.user.userId = :userId AND l.post.postId = :postId")
    void deleteByUserAndPost(Long userId, Long postId);
    @Modifying
    @Transactional
    @Query("DELETE FROM Like l WHERE l.post.postId = :postId")
    void deleteAllLikeForPost(Long postId);
    @Query("SELECT COUNT(l) FROM Like l WHERE l.post.postId = :postId")
    long countByPostId(Long postId);
    boolean existsByUserAndPost(User user,Post post);
}
