package com.example.StudyWithMe.repositories.exercise.question;

import com.example.StudyWithMe.models.study.question.Question;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("SELECT q FROM Question q WHERE q.user.userId = :userId")
    Page<Question> findByUserId(Long userId, PageRequest pageRequest);

    @Query("SELECT q FROM Question q WHERE q.subject.subjectId = :subjectId")
    Page<Question> findBySubjectId(Long subjectId, PageRequest pageRequest);

    @Modifying
    @Transactional
    @Query("DELETE FROM Question q WHERE q.questionId = :questionId")
    void deleteQuestion(Long questionId);
}
