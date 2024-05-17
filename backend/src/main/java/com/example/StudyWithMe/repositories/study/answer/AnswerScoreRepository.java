package com.example.StudyWithMe.repositories.exercise.answer;

import com.example.StudyWithMe.models.study.answer.AnswerScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
public interface AnswerScoreRepository extends JpaRepository<AnswerScore,Long> {
    @Query("SELECT s FROM AnswerScore s WHERE s.answerId = :answerId")
    List<AnswerScore> getAllAnswerScore(Long answerId);
    @Transactional
    @Modifying
    @Query("UPDATE AnswerScore s SET s.score = :score WHERE s.answerId = :answerId")
    void updateAnswerScore(Long answerId, int score);
}
