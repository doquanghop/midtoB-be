package com.example.StudyWithMe.repositories.exercise.question;

import com.example.StudyWithMe.models.study.question.QuestionAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface QuestionAttachmentRepository extends JpaRepository<QuestionAttachment, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM QuestionAttachment qa WHERE qa.question.questionId = :questionId")
    void deleteByQuestionId(Long questionId);
}
