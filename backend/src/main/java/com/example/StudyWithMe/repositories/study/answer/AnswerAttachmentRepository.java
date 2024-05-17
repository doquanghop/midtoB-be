package com.example.StudyWithMe.repositories.exercise.answer;

import com.example.StudyWithMe.models.study.answer.AnswerAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface AnswerAttachmentRepository extends JpaRepository<AnswerAttachment, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM AnswerAttachment aa WHERE aa.answer.answerId = :answerId")
    void deleteByAnswerId(Long answerId);
}
