package com.example.StudyWithMe.repositories.socialmedia.comment;

import com.example.StudyWithMe.models.socialmedia.comment.ReplyCommentAttachment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyCommentAttachmentRepository extends JpaRepository<ReplyCommentAttachment,Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM ReplyCommentAttachment a WHERE a.replyComment.replyCommentId = :replyCommentId")
    void deleteAllAttachmentForReplyComment(Long replyCommentId);
    @Query("SELECT a FROM ReplyCommentAttachment a WHERE a.replyComment.replyCommentId = :replyCommentId")
    List<ReplyCommentAttachment> findAllByReplyCommentId(Long replyCommentId);
}
