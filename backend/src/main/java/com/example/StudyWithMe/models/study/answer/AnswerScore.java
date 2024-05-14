package com.example.StudyWithMe.models.exercise.answer;

import com.example.StudyWithMe.models.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "answer_scores")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class AnswerScore extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "score_id")
    private Long scoreId;
    @Column(name = "answer_id")
    private Long answerId;
    @Column(name = "user_id")
    private Long userId;
    private int score;
}