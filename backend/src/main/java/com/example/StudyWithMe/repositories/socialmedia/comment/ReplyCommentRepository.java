package com.example.StudyWithMe.repositories.socialmedia.comment;

import com.example.StudyWithMe.models.socialmedia.comment.ReplyComment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface ReplyCommentRepository extends JpaRepository<ReplyComment,Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM ReplyComment c WHERE c.comment.commentId = :commentId")
    void deleteAllReplyForComment(Long commentId);
    @Query("SELECT r FROM ReplyComment r WHERE r.comment.commentId = :commentId")
    List<ReplyComment> findAllByCommentId(Long commentId);
}
