package com.example.StudyWithMe.repositories.exercise.answer;

import com.example.StudyWithMe.models.exercise.answer.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Query("SELECT a FROM Answer a WHERE a.question.questionId = :questionId")
    Page<Answer> findByQuestionId(Long questionId, PageRequest pageRequest);

    @Modifying
    @Transactional
    @Query("DELETE FROM Answer a WHERE a.question.questionId = :questionId")
    void deleteByQuestionId(Long questionId);
}
