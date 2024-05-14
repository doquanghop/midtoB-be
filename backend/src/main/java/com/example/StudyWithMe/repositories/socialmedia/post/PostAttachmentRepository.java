package com.example.StudyWithMe.repositories.socialmedia.post;

import com.example.StudyWithMe.models.socialmedia.post.PostAttachment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostAttachmentRepository extends JpaRepository<PostAttachment,Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM PostAttachment a WHERE a.post.postId = :postId")
    void deleteByPost(Long postId);
    @Query("SELECT a FROM PostAttachment a WHERE a.post.postId = :postId")
    List<PostAttachment> findAllByPostId(Long postId);
}
