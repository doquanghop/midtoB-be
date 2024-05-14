package com.example.StudyWithMe.repositories.socialmedia.comment;

import com.example.StudyWithMe.models.socialmedia.comment.Comment;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface CommentRepository extends JpaRepository<Comment,Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Comment c WHERE c.post.postId = :postId")
    void deleteAllCommentForPost(Long postId);
    @Query("SELECT c FROM Comment c WHERE c.post.postId = :postId")
    List<Comment> findAllByPostId(Long postId, PageRequest pageRequest);
    @Query("SELECT c FROM Comment c WHERE c.post.postId = :postId")
    List<Comment> findAllByPostId(Long postId);
    @Query("SELECT COUNT(c) FROM Comment c WHERE c.post.postId = :postId")
    long countByPostId(Long postId);
}
