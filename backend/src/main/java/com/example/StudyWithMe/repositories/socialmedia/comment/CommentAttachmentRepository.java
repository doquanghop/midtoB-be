package com.example.StudyWithMe.repositories.socialmedia.comment;

import com.example.StudyWithMe.models.socialmedia.comment.CommentAttachment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface CommentAttachmentRepository extends JpaRepository<CommentAttachment,Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM CommentAttachment c WHERE c.comment.commentId = :commentId")
    void deleteAllAttachmentForComment(Long commentId);
    @Modifying
    @Transactional
    @Query("SELECT c FROM CommentAttachment c WHERE c.comment.commentId = :commentId")
    List<CommentAttachment> findAllByCommentId(Long commentId);
}
